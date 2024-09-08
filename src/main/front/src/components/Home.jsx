import React, { useEffect } from "react";
import axios from "axios";
import Book from "./Book.jsx";
import { useDebounce } from "../customHook/useDebounce.jsx";
import Search from "./Search.jsx";
import { BookData } from "./Data.jsx";

const Home = () => {
  const [data, setData] = React.useState([]);
  const [display, setDisplay] = React.useState(10);
  const [total, setTotal] = React.useState(0);
  const [loading, setLoading] = React.useState(false);
  const [error, setError] = React.useState(null);
  const [title, setTitle] = React.useState("");
  const debouncedTitle = useDebounce(title, 1000);
  const [top10Data, setTop10Data] = React.useState([]);
  const [top10Loading, setTop10Loading] = React.useState(false);
  const [top10Reload, setTop10Reload] = React.useState(false);

  const getData = async () => {
    setLoading(true);
    setData([]);
    setError(null);

    try {
      const response = await axios
        .get("/api/books?title=" + title + "&display=" + display + "&start=1")
        .then((res) => {
          setData(res.data.books.map((book) => new BookData(book)));
          setTotal(res.data.total);
          setLoading(false);
        });
    } catch (e) {
      setError(e);
    }
  };

  const getTop10Data = async () => {
    setTop10Loading(true);
    try {
      const response = await axios.get("/api/books/top10").then((res) => {
        setTop10Data(res.data.map((book) => new BookData(book)));
        setTop10Loading(false);
      });
    } catch (e) {
      console.log(e);
    }
  };

  const searchHandle = (e) => {
    setTitle(e.target.value);
    e.target.value === "" && setData([]);
  };

  useEffect(() => {
    title.length !== 0 && getData();
  }, [debouncedTitle]);

  useEffect(() => {
    getTop10Data();
  }, [top10Reload]);

  return (
    <div className="w-[100vw] h-[100vh] flex flex-col overflow-x-hidden">
      <Search title={title} searchHandle={searchHandle} />
      <section className="mt-28">
        {debouncedTitle.length !== 0 ? (
          <div className="flex justify-center mt-6">
            {data.length !== 0 ? (
              <div>
                {loading ? (
                  <p>Loading...</p>
                ) : (
                  <>
                    <p className="text-sm text-gray-600 mb-3">total {total}</p>
                    <div className="max-w-[900px] inline-grid grid-cols-3 sm:grid-cols-4 md:grid-cols-5 gap-4">
                      {data.map((book, index) => {
                        return <Book key={index} book={book} />;
                      })}
                    </div>
                  </>
                )}
              </div>
            ) : (
              <p>찾으시는 책이 없습니다 🥲</p>
            )}
          </div>
        ) : (
          <div>
            <h1 className="font-bold text-3xl mb-5"># Top 10</h1>
            {top10Loading ? (
              <></>
            ) : (
              <div className="flex gap-5 whitespace-nowrap overflow-x-auto">
                {top10Data.map((book, index) => {
                  return <Book key={index} book={book} />;
                })}
              </div>
            )}
          </div>
        )}
      </section>
    </div>
  );
};

export default Home;
