import React, { useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ShoppingCart = () => {
  const [shoppingCartBooks, setShoppingCartBooks] = React.useState([]);
  const [loading, setLoading] = React.useState(false);
  const [username, setUsername] = React.useState("admin2");
  const navigate = useNavigate();

  const getShoppingCart = async () => {
    try {
      await axios.get("/api/shopping-cart?username=" + username).then((res) => {
        console.log(res.data);
        setShoppingCartBooks(res.data);
        setLoading(false);
      });
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    setLoading(true);
    getShoppingCart();
  }, []);

  return (
    <div className="w-full mt-6 flex flex-col items-center">
      <h1 className="font-bold text-xl mb-6">장바구니</h1>
      {loading ? (
        <div>loading...</div>
      ) : (
        <div className="w-full">
          {shoppingCartBooks.map((book, index) => {
            return (
              <div
                className={`${!book.sold ? "w-full" : "w-full opacity-40 pointer-events-none"}`}
                key={index}
              >
                <div className="flex p-8 border-b-[1px] flex-col lg:flex-row justify-between">
                  <div className="flex items-center">
                    <img
                      src={book.image}
                      alt="book cover image"
                      className="w-[80px] mr-6 cursor-pointer"
                      onClick={() => {
                        navigate("/book/" + book.isbn, {
                          state: {
                            book: {
                              title: book.title,
                              image: book.image,
                              author: book.author,
                              publisher: book.publisher,
                              price: book.originPrice,
                              isbn: book.isbn,
                              description: book.description,
                            },
                          },
                        });
                      }}
                    />
                    <div>
                      <h1 className="mb-1 font-bold">{book.title}</h1>
                      <p className="text-sm mb-3">{book.publisher}</p>
                      <p className="text-sm">{book.author}</p>
                    </div>
                  </div>
                  <div className="flex justify-end items-center justify-between mt-8">
                    <div className="flex gap-5 mr-24">
                      <div className="flex gap-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          fill="none"
                          viewBox="0 0 24 24"
                          strokeWidth={1.5}
                          stroke="currentColor"
                          className="size-6"
                        >
                          <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z"
                          />
                        </svg>
                        <div className="mr-3 font-bold">{book.sellerName}</div>
                      </div>
                      <div className="line-through text-gray-400">
                        {book.originPrice} 원
                      </div>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={1.5}
                        stroke="currentColor"
                        className="size-6"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M17.25 8.25 21 12m0 0-3.75 3.75M21 12H3"
                        />
                      </svg>

                      <div>{book.price} 원</div>
                    </div>
                    <div className="flex gap-2 items-center">
                      <input type="checkbox" id="check" className="mr-5" />
                      <button>
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          fill="none"
                          viewBox="0 0 24 24"
                          strokeWidth={1.5}
                          stroke="currentColor"
                          className="size-6"
                        >
                          <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0"
                          />
                        </svg>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default ShoppingCart;
