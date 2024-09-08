import React from "react";
import axios from "axios";

const ForSaleBook = ({ forSaleBook, number }) => {
  const addBookToShoppingCart = async () => {
    try {
      await axios
        .post("/api/shopping-cart/register", {
          forSalesBookId: forSaleBook.id,
          sellerName: forSaleBook.sellerName,
        })
        .then((res) => console.log(res.data));
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="flex items-center h-20 p-3 border-b-2">
      <div className="flex items-center flex-1">
        <p className="mr-5">{number + 1}</p>
        <p className="mr-5">{forSaleBook.sellerName}</p>
        <p className="mr-5">{forSaleBook.price} 원</p>
        <p>{forSaleBook.condition}</p>
      </div>
      <button
        className="rounded px-4 py-2 bg-blue-500 hover:bg-blue-700 text-white"
        onClick={() => addBookToShoppingCart()}
      >
        장바구니 담기
      </button>
    </div>
  );
};

export default ForSaleBook;
