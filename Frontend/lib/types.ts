export interface Product {
  id: string
  name: string
  description: string
  price: number
  stock: number
  image?: string
}

export interface CartItem {
  id: string
  name: string
  price: number
  quantity: number
  image?: string
}

