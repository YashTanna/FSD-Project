// import './App.css'

import Footer from './customer/components/Footer/Footer'
import Navigation from './customer/components/Navigation/Navigation'
import Product from './customer/components/Product/Product'
import HomePage from './customer/pages/HomePage/HomePage'

function App() {

  return (
    <>
      <Navigation/>
      <div>
        {/* <HomePage/> */}
        <Product/>
        {/* <Items/> */}
      </div>
      <Footer/>
    </>
  )
}

export default App
