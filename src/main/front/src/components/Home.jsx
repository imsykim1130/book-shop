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
    const url = "/v1/search/book_adv.xml"
    const client_id = import.meta.env.VITE_CLIENT_ID;
    const client_secret = import.meta.env.VITE_CLIENT_SECRET;

    const getData = async () => {
        setLoading(true);
        setData([]);
        setError(null);

        try {
            const response = await axios.get(encodeURI("api/books?title=" + title))
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
            <header>
                <div className="w-full flex items-center justify-between px-7 pt-7">
                    <div className="logo">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
                             className="size-6">
                            <path
                                d="M11.25 4.533A9.707 9.707 0 0 0 6 3a9.735 9.735 0 0 0-3.25.555.75.75 0 0 0-.5.707v14.25a.75.75 0 0 0 1 .707A8.237 8.237 0 0 1 6 18.75c1.995 0 3.823.707 5.25 1.886V4.533ZM12.75 20.636A8.214 8.214 0 0 1 18 18.75c.966 0 1.89.166 2.75.47a.75.75 0 0 0 1-.708V4.262a.75.75 0 0 0-.5-.707A9.735 9.735 0 0 0 18 3a9.707 9.707 0 0 0-5.25 1.533v16.103Z"/>
                        </svg>
                    </div>
                    <nav className="flex items-center text-sm">
                        <span className="pl-6">HOME</span>
                        <span className="pl-6">BOARD</span>
                        <span className="pl-6">MY PAGE</span>
                    </nav>

                </div>
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
            </header>
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