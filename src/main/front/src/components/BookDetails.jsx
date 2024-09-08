import React from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";
import ForSaleBook from "./ForSaleBook.jsx";

const BookDetails = () => {
  const { state } = useLocation();
  const { book } = state;
  const [condition, setCondition] = React.useState("MIDDLE");
  const [modalOpen, setModalOpen] = React.useState(false);
  const [forSaleData, setForSaleData] = React.useState([]);
  const [forSaleLoading, setForSaleLoading] = React.useState(false);

  const handleChange = (e) => {
    setCondition(e.target.value);
  };

  const registerBook = async () => {
    try {
      await axios
        .post("/api/register/book", {
          username: "admin2",
          condition: condition,
          bookDto: book,
        })
        .then((res) => console.log(res.data));
    } catch (e) {
      console.log(e);
    }
  };

  const getForSaleData = async () => {
    try {
      await axios.get("/api/for-sales-books?isbn=" + book.isbn).then((res) => {
        setForSaleData(res.data);
        console.log(res.data);
        setForSaleLoading(false);
      });
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    setForSaleLoading(true);
    getForSaleData();
  }, []);

  return (
    <div className="flex flex-col items-center mx-[15%] md:items-start">
      {/* 책 상세정보 */}
      <div className="flex flex-col items-center md:items-start md:flex-row">
        <div className="mt-3 flex flex-col items-center">
          <div className="w-[200px] md:w-[300px] ">
            <img src={book.image} alt="book cover image" />
          </div>
          <button
            className="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded"
            onClick={() => setModalOpen(true)}
          >
            판매하기
          </button>
        </div>
        <div className="flex-1 md:ml-20 text-center md:text-left">
          <h1 className="text-3xl font-bold mt-5">{book.title}</h1>
          <p className="mt-2 text-sm text-gray-600">{book.publisher}</p>
          <p className="mt-1 text-sm text-gray-900">{book.author}</p>
          <p className="mt-10">{book.description}</p>
        </div>
      </div>

      {/* modal */}
      {modalOpen && (
        <div className="absolute top-1/3 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
          <div className="relative p-4 w-full max-w-md max-h-full">
            <div className="relative bg-white rounded-lg shadow dark:bg-gray-700">
              <div className="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
                  책 판매 등록
                </h3>
                <button
                  type="button"
                  className="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm h-8 w-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                  onClick={() => setModalOpen(false)}
                >
                  <svg
                    className="w-3 h-3"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 14 14"
                  >
                    <path
                      stroke="currentColor"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                    />
                  </svg>
                  <span className="sr-only">Close modal</span>
                </button>
              </div>

              <div className="p-4 md:p-5">
                <p className="text-sm font-normal text-gray-500 dark:text-gray-400">
                  판매하고자 하는 책의 상태를 선택해주세요.
                  <br />책 상태에 따라 정해진 할인율이 원가에 적용되어 최종
                  판매가격이 됩니다.
                </p>
                <fieldset className="text-gray-200 grid gap-3 my-5">
                  <div>
                    <input
                      type="radio"
                      value="HIGH"
                      id="high"
                      className="mr-2"
                      checked={condition === "HIGH"}
                      onChange={handleChange}
                    />
                    <label htmlFor="high">상 - 70%</label>
                  </div>
                  <div>
                    <input
                      type="radio"
                      value="MIDDLE"
                      id="middle"
                      className="mr-2"
                      checked={condition === "MIDDLE"}
                      onChange={handleChange}
                    />
                    <label htmlFor="middle">중 - 50%</label>
                  </div>
                  <div>
                    <input
                      type="radio"
                      value="LOW"
                      id="low"
                      className="mr-2"
                      checked={condition === "LOW"}
                      onChange={handleChange}
                    />
                    <label htmlFor="low">하 - 30%</label>
                  </div>
                </fieldset>

                <button
                  className="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                  onClick={() => registerBook()}
                >
                  판매하기
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* 판매중인 책 */}
      <div className="w-full mt-6">
        <h1 className="font-bold text-xl mb-3">판매중인 책</h1>
        {forSaleLoading ? (
          <div>loading...</div>
        ) : (
          forSaleData.map((book, index) => {
            return (
              <ForSaleBook key={index} forSaleBook={book} number={index} />
            );
          })
        )}
      </div>
    </div>
  );
};

export default BookDetails;
