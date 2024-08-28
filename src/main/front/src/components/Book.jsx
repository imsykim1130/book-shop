import React from 'react';
import {Link, useNavigate} from "react-router-dom";

const Book = ({imgSrc, title, discount, isbn}) => {
    const navigate = useNavigate();

    const clickHandler = () => {
         navigate("/books/" + isbn);
    }

    return (
        <div>
            <div className="w-full flex flex-col text-xs mb-5" onClick={clickHandler}>
                <img src={imgSrc} alt="book-image" className="cursor-pointer"/>
                <h1 className="font-bold mt-3">{title}</h1>
                <span className="mt-1">{discount !== "0" ? discount + " won" : "절판"}</span>
            </div>
        </div>
    );
};

export default Book;