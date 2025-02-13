import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Navigation from './customer/components/navigation/Navigation'
import HomePage from './customer/pages/HomePage/HomePage'
import Items from './customer/components/test/Items'
import tractor from './customer/components/test/tractor.jpg'
import Image from './customer/components/test/image.jpg'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navigation />
      <div>
        <HomePage/>
        {/* <Items/> */}
      </div>
    </>
  )
}

export default App
