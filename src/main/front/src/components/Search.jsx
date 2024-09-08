import React from "react";
import path from "node:path";

const Search = ({ title, searchHandle }) => {
  return (
    <div className="w-full flex items-center mt-5 absolute left-0">
      <div className="w-full flex items-center shadow-md px-7">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="size-6 relative"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"
          />
        </svg>
        <input
          className="w-full px-5 pl-4 py-4 outline-none"
          type="text"
          value={title}
          placeholder="구매할 책을 찾아보세요"
          onChange={searchHandle}
        />
      </div>
    </div>
  );
};

export default Search;
