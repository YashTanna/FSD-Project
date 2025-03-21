"use client"

import { useState, useEffect } from "react"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { Plus } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import ProductTable from "@/components/product-table"
import { useToast } from "@/hooks/use-toast"
import { getProducts, getCurrentUser } from "@/lib/api"

export default function AdminPage() {
  const router = useRouter()
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [searchQuery, setSearchQuery] = useState("")
  const [user, setUser] = useState(null)
  const [authChecking, setAuthChecking] = useState(true)
  const { toast } = useToast()

  // Check if user is authenticated and is admin
  useEffect(() => {
    const checkAuth = async () => {
      try {
        const userData = await getCurrentUser()
        setUser(userData)

        if (!userData || userData.role !== "admin") {
          toast({
            title: "Access Denied",
            description: "You don't have permission to access the admin panel.",
            variant: "destructive",
          })
          router.push("/")
        }
      } catch (error) {
        toast({
          title: "Authentication Error",
          description: "Please log in to access this page.",
          variant: "destructive",
        })
        router.push("/login")
      } finally {
        setAuthChecking(false)
      }
    }

    checkAuth()
  }, [router, toast])

  useEffect(() => {
    const fetchProducts = async () => {
      if (authChecking) return

      try {
        setLoading(true)
        const data = await getProducts()
        setProducts(data)
      } catch (error) {
        console.error("Failed to fetch products:", error)
        toast({
          title: "Error",
          description: error.message || "Failed to load products. Please try again later.",
          variant: "destructive",
        })
      } finally {
        setLoading(false)
      }
    }

    fetchProducts()
  }, [authChecking, toast])

  const handleDeleteProduct = (id) => {
    setProducts(products.filter((product) => product.id !== id))
    toast({
      title: "Product deleted",
      description: "The product has been successfully deleted.",
    })
  }

  const filteredProducts = products.filter(
    (product) =>
      product.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      product.description.toLowerCase().includes(searchQuery.toLowerCase()),
  )

  if (authChecking) {
    return (
      <div className="container mx-auto px-4 py-8 text-center">
        <h1 className="text-3xl font-bold mb-6">Loading...</h1>
      </div>
    )
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Admin Dashboard</h1>
        <Link href="/">
          <Button variant="outline">Back to Store</Button>
        </Link>
      </div>

      <div className="bg-card rounded-lg border shadow-sm p-6 mb-8">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold">Product Management</h2>
          <Link href="/admin/products/new">
            <Button>
              <Plus className="mr-2 h-4 w-4" /> Add New Product
            </Button>
          </Link>
        </div>

        <div className="mb-6">
          <Input
            placeholder="Search products..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="max-w-md"
          />
        </div>

        <ProductTable products={filteredProducts} loading={loading} onDelete={handleDeleteProduct} />
      </div>
    </div>
  )
}

