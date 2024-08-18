import React from 'react';
import gsap from 'gsap';
import {useGSAP} from "@gsap/react";

const Search = () => {
    return (
        <div className="w-screen flex items-center shadow-md pr-7 pl-7">
            <div className="flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                     stroke="currentColor" className="size-6">
                    <path strokeLinecap="round" strokeLinejoin="round"
                          d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"/>
                </svg>
                <input
                    className="w-[300px] pr-5 pl-5 p-4 outline-none"
                    type="text"
                    name="search"
                    placeholder="Search all book..."
                />
            </div>
        </div>
    );
};

export default Search;