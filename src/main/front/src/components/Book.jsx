import React from 'react';

const Book = ({imgSrc, title}) => {
    return (
        <div>
            <div className="w-full flex flex-col justify-center">
                <img src={imgSrc} alt="book-image"/>
                <div>
                    <h1 className="font-bold text-xs">{title}</h1>
                </div>
            </div>
        </div>
    );
};

export default Book;