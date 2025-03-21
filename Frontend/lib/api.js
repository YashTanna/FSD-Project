// Updated API service to use real backend endpoints

export async function registerUser(userData) {
  const res = await fetch("https://localhost:8080/api/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userData),
  });
  if (!res.ok) throw new Error("Registration failed");
  return res.json();
}

export async function loginUser(credentials) {
  const res = await fetch("https://localhost:8080/api/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(credentials),
  });
  if (!res.ok) throw new Error("Invalid credentials");
  return res.json();
}


// Helper function for API requests
async function fetchAPI(endpoint, options = {}) {
  const response = await fetch(`http://localhost:8080/api${endpoint}`, {
    headers: {
      "Content-Type": "application/json",
      ...options.headers,
    },
    ...options,
  })

  if (!response.ok) {
    const error = await response.json().catch(() => ({}))
    throw new Error(error.error || `API error: ${response.status}`)
  }

  return response.json()
}

// Product APIs
export async function getProducts() {
  const data = await fetchAPI("/products")
  return data.products
}

export async function getProduct(id) {
  const data = await fetchAPI(`/products/${id}`)
  return data.product
}

export async function createProduct(product) {
  const data = await fetchAPI("/products", {
    method: "POST",
    body: JSON.stringify(product),
  })
  return data.product
}

export async function updateProduct(product) {
  const { id, ...productData } = product
  const data = await fetchAPI(`/products/${id}`, {
    method: "PUT",
    body: JSON.stringify(productData),
  })
  return data.product
}

export async function deleteProduct(id) {
  await fetchAPI(`/products/${id}`, {
    method: "DELETE",
  })
  return true
}

// Cart APIs
export async function getCart() {
  const data = await fetchAPI("/cart")
  return data.items
}

export async function addToCart(productId, quantity = 1) {
  const data = await fetchAPI("/cart", {
    method: "POST",
    body: JSON.stringify({ productId, quantity }),
  })
  return data.item
}

export async function updateCartItem(itemId, quantity) {
  const data = await fetchAPI(`/cart/${itemId}`, {
    method: "PUT",
    body: JSON.stringify({ quantity }),
  })
  return data.item
}

export async function removeCartItem(itemId) {
  await fetchAPI(`/cart/${itemId}`, {
    method: "DELETE",
  })
  return true
}

// Auth APIs
export async function login(email, password) {
  const data = await fetchAPI("/auth/login", {
    method: "POST",
    body: JSON.stringify({ email, password }),
  })

  // Store token in localStorage or cookies
  if (data.token) {
    localStorage.setItem("auth_token", data.token)
  }

  return data.user
}

export async function logout() {
  localStorage.removeItem("auth_token")
  await fetchAPI("/auth/logout", {
    method: "POST",
  })
  return true
}

export async function getCurrentUser() {
  try {
    const data = await fetchAPI("/auth/me")
    return data.user
  } catch (error) {
    // If unauthorized or token expired
    localStorage.removeItem("auth_token")
    return null
  }
}

// Order APIs
export async function createOrder(orderData) {
  const data = await fetchAPI("/orders", {
    method: "POST",
    body: JSON.stringify(orderData),
  })
  return data.order
}

export async function getOrders() {
  const data = await fetchAPI("/orders")
  return data.orders
}

export async function getOrder(id) {
  const data = await fetchAPI(`/orders/${id}`)
  return data.order
}

