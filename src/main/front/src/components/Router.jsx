import React from "react";
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import Home from "./Home.jsx";
import BookDetails from "./BookDetails.jsx";
import Header from "./Header.jsx";

const Router = () => {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/book/:isbn" element={<BookDetails />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default Router;
