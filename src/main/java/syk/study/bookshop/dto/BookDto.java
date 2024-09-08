package syk.study.bookshop.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.xml.Item;

@Getter
@NoArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private int price;
    private String image;

    public static BookDto newBookDto(Item item) {
        BookDto bookDto = new BookDto();
        bookDto.isbn = item.getIsbn();
        bookDto.title = item.getTitle();
        bookDto.author = item.getAuthor();
        bookDto.publisher = item.getPublisher();
        bookDto.description = item.getDescription();
        bookDto.price = item.getDiscount();
        bookDto.image = item.getImage();
        return bookDto;
    }

    public static BookDto newBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.isbn = book.getIsbn();
        bookDto.title = book.getTitle();
        bookDto.author = book.getAuthor();
        bookDto.publisher = book.getPublisher();
        bookDto.description = book.getDescription();
        bookDto.price = book.getPrice();
        bookDto.image = book.getImage();
        return bookDto;
    }
}
