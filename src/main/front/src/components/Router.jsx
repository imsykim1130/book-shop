import React from 'react';
import {BrowserRouter, Route, Routes, useNavigate} from "react-router-dom";
import Home from "./Home.jsx";
import Book from "./Book.jsx";
import BookDetails from "./BookDetails.jsx";
import Header from "./Header.jsx";

const Router = () => {
    return (
        <>
            <Header/>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/books/:isbn" element={<BookDetails/>}/>
                </Routes>
            </BrowserRouter>
        </>
    );
};

export default Router;