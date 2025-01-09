import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Navigation from './customer/components/navigation/Navigation'
import HomePage from './customer/pages/HomePage/HomePage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navigation/>
      <div>
        <HomePage/>
      </div>
    </>
  )
}

export default App
