package syk.study.bookshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    private String isbn;
    private String title;
    private String infoLink;
    private String imgLink;
    private String author;
    private String discount;
    private String publisher;
    private String description;
    private String pubDate;

    public Book createBook(String isbn, String title, String infoLink, String imgLink, String author, String discount, String publisher, String description, String pubDate) {
        Book book = new Book();
        
        book.isbn = isbn;
        book.title = title;
        book.infoLink = infoLink;
        book.imgLink = imgLink;
        book.author = author;
        book.discount = discount;
        book.publisher = publisher;
        book.description = description;
        book.pubDate = pubDate;

        return book;
    }
}
