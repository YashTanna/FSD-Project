import Link from "next/link"
import { ShoppingCart } from "lucide-react"

import { Button } from "@/components/ui/button"
import ProductList from "@/components/product-list"

export default function Home() {
  return (
    <div className="container mx-auto px-4 py-8">
      <header className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold">ShopEasy</h1>
        <div className="flex items-center gap-4">
          <Link href="/admin" className="text-sm font-medium hover:underline">
            Admin Panel
          </Link>
          <Link href="/cart">
            <Button variant="outline" size="icon">
              <ShoppingCart className="h-5 w-5" />
              <span className="sr-only">Shopping Cart</span>
            </Button>
          </Link>
        </div>
      </header>

      <main>
        <section className="mb-10">
          <div className="bg-muted rounded-lg p-8 mb-8">
            <h2 className="text-4xl font-bold mb-4">Summer Sale</h2>
            <p className="text-lg mb-6">Get up to 40% off on selected items</p>
            <Button size="lg">Shop Now</Button>
          </div>
        </section>

        <section>
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-2xl font-bold">Featured Products</h2>
            <Link href="/products" className="text-sm font-medium text-primary hover:underline">
              View All
            </Link>
          </div>
          <ProductList />
        </section>
      </main>
    </div>
  )
}

