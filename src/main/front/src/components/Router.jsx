import React from "react";
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import Home from "./Home.jsx";
import BookDetails from "./BookDetails.jsx";
import Header from "./Header.jsx";
import ShoppingCart from "./ShoppingCart.jsx";

const Router = () => {
  return (
    <>
      <BrowserRouter>
        <div className="mx-7">
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/book/:isbn" element={<BookDetails />} />
            <Route path="/shopping-cart" element={<ShoppingCart />} />
          </Routes>
        </div>
      </BrowserRouter>
    </>
  );
};

export default Router;
