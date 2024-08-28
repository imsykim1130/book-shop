import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";

const BookDetails = () => {
    const {isbn} = useParams();
    const [data, setData] = useState({});
    const [loading, setLoading] = React.useState(false);
    const [error, setError] = React.useState(null);
    const [condition, setCondition] = React.useState("MIDDLE");
    const [title, setTitle] = React.useState("");
    const [post, setPost] = React.useState("");

    const getData = async () => {

        setLoading(true);
        setData([]);
        setError(null);

        try {
            const response = await axios.get("/api/books/is?isbn=" + isbn)
                .then((res) => {
                    console.log(res.data.items[0]);
                    setData(res.data.items[0]);
                    setLoading(false);
                })

        } catch (e) {
            setError(e);
        }

    }

    const handleChange = (e) => {
        setCondition(e.target.value);
        console.log(e.target.value);
    }

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    }

    const handlePostChange = (e) => {
        setPost(e.target.value);
    }


    const addBookToShoppingCart = async () => {
        try {
            await axios.post("/api/shopping-cart", {
                username: "admin",
                isbn: isbn,
                condition: condition,
                title: title,
                post: post,
            })
                .then(res=>console.log(res.data));
        } catch (e) {
            console.log(e);
        }
    }

    const handleClickShoppingCart = (e) => {
        e.preventDefault();
        addBookToShoppingCart();
    }


    useEffect(() => {
        !loading && getData();
    }, []);

    return (
        <div className="w-full flex flex-col items-center">
            <div className="flex flex-col items-center">
                {
                    loading ?
                        <p>loading...</p> :
                        <div className="w-4/5 flex flex-col justify-between items-center">
                            <div className="w-[200px] mt-3">
                                <img src={data.image} alt="book cover image"/>
                            </div>
                            <h1 className="text-3xl font-bold mt-5">{data.title}</h1>
                            <p className="mt-3 font-bold">{data.author}</p>
                        </div>
                }
            </div>
            <form className="w-4/5 flex flex-col mt-8 text-sm">
                <div className="flex flex-3 mb-5">
                    <div className="flex-1">
                        <h3>책 상태</h3>
                        <div className="mt-2 flex">
                            <div className="flex items-center mr-6">
                                <input  checked={condition === "HIGH"}
                                        id="default-radio-1" type="radio" value="HIGH" name="default-radio"
                                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                        onClick={handleChange}
                                />
                                <label htmlFor="default-radio-1"
                                       className="ms-2 text-sm font-medium text-gray-500">상</label>
                            </div>
                            <div className="flex items-center mr-6">
                                <input checked={condition === "MIDDLE"}
                                       id="default-radio-2" type="radio" value="MIDDLE" name="default-radio"
                                       className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                       onClick={handleChange}
                                />
                                <label htmlFor="default-radio-2"
                                       className="ms-2 text-sm font-medium text-gray-500">중</label>
                            </div>
                            <div className="flex items-center mr-6">
                                <input checked={condition === "LOW"}
                                       id="default-radio-3" type="radio" value="LOW" name="default-radio"
                                       className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                                       onClick={handleChange}
                                />
                                <label htmlFor="default-radio-3"
                                       className="ms-2 text-sm font-medium text-gray-500">하</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="flex-1 mb-6">
                    <div className="mb-5">
                        <h3>제목</h3>
                        <input type="text"
                               className="focus:outline-none bg-gray-200 px-3 py-1.5 mt-1 w-full"
                                value={title}
                               onChange={handleTitleChange}
                        />
                    </div>
                    <div>
                        <h3>본문</h3>
                        <textarea
                            className="w-full h-[200px] bg-gray-200 mt-1 p-3"
                            value={post}
                            onChange={handlePostChange}
                        />
                    </div>
                </div>
                <input
                    type="submit"
                    value="판매 장바구니 등록"
                    className="bg-gray-200 p-3 mb-10 hover:bg-amber-400"
                    onClick={handleClickShoppingCart}
                />
            </form>
        </div>
    );
};

export default BookDetails;