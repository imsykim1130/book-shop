import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Book = ({ book }) => {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => {
        navigate("/book/" + book.isbn, {
          state: {
            book: book,
          },
        });
      }}
    >
      <div className="w-[150px] flex flex-col text-xs mb-5">
        <img src={book.image} alt="book-image" className="cursor-pointer" />
        <h1 className="font-bold mt-3 whitespace-normal">{book.title}</h1>
      </div>
    </div>
  );
};

export default Book;
