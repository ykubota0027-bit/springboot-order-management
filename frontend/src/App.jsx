import { Routes, Route } from "react-router-dom";
import { useState } from "react";
import HomePage from "./pages/HomePage";
import ProductListPage from "./pages/ProductListPage";
import ProductCreatePage from "./pages/ProductCreatePage";
import ProductDetailPage from "./pages/ProductDetailPage";

function App() {
  const [products, setProducts] = useState([
    { id: 1, name: "Mouse", price: 1000, stock: 10, category: "PC周辺機器" },
    { id: 2, name: "Keyboard", price: 5000, stock: 5, category: "PC周辺機器" },
    { id: 3, name: "Monitor", price: 20000, stock: 2, category: "PC周辺機器" }
  ]);

  const addProduct = (newProduct) => {
    const productToAdd = {
      ...newProduct,
      id: Date.now(),
      price: Number(newProduct.price),
      stock: Number(newProduct.stock)
    };

    setProducts((prev) => [...prev, productToAdd]);
  };

  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route
        path="/products"
        element={<ProductListPage products={products} />}
      />
      <Route
        path="/products/new"
        element={<ProductCreatePage onAddProduct={addProduct} />}
      />
      <Route
        path="/products/:id"
        element={<ProductDetailPage products={products} />}
      />
    </Routes>
  );
}

export default App;