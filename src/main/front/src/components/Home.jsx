import React, {useEffect} from 'react';
import axios from "axios";
import Book from "./Book.jsx";
import {useDebounce} from "../customHook/useDebounce.jsx";

const Home = () => {
    const [data, setData] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [error, setError] = React.useState(null);
    const [title, setTitle] = React.useState("");
    const debouncedTitle = useDebounce(title, 1000);

    const getData = async () => {
        setLoading(true);
        setData([]);
        setError(null);

        try {
            const response = await axios.get(encodeURI("api/books/t?title=" + title))
                .then((res) => {
                    console.log(res.data);
                    setData(res.data.items);
                    setLoading(false);
                })

        } catch (e) {
            setError(e);
        }
    }

    const searchHandle = (e) => {
        setTitle(e.target.value);
        e.target.value === "" && setData([]);
    }


    useEffect(()=>{
        (title.length !== 0) && getData();
    }, [debouncedTitle]);

    return (
        <div className="w-[100vw] h-[100vh] flex flex-col overflow-x-hidden">
            <div>
                <div className="w-screen h-[25vh] flex flex-col items-center justify-center font-bold text-5xl">
                    <p>This is</p>
                    <p>Example</p>
                </div>
                <div className="w-screen flex items-center shadow-md pr-7 pl-7">
                    <div className="flex items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                             stroke="currentColor" className="size-6">
                            <path strokeLinecap="round" strokeLinejoin="round"
                                  d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"/>
                        </svg>
                        <input
                            className="px-5 py-4 outline-none"
                            type="text"
                            value={title}
                            placeholder="Search all book..."
                            onChange={searchHandle}
                        />
                    </div>
                </div>

            </div>

            <section className="flex justify-center mt-8 mx-7">
                <div className="max-w-[900px] inline-grid grid-cols-3 sm:grid-cols-4 md:grid-cols-5 gap-4">
                    {
                        data.length !== 0 ?
                            loading ? <p>Loading...</p> : (
                                data.map((book, index) => {
                                    return <Book
                                        key={index}
                                        title={book.title}
                                        imgSrc={book.image}
                                        discount={book.discount}
                                        isbn={book.isbn}
                                    />
                                })
                            ) : <p>No book found.</p>
                    }
                </div>

            </section>
        </div>
    );
};

export default Home;