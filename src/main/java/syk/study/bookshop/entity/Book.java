package syk.study.bookshop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import syk.study.bookshop.dto.BookDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    @Column(length = 20000)
    private String description;
    private int price;
    private String image;
    private int stock;
    private int salesVolume;

    public static Book makeNewBook(BookDto bookDto) {
        Book book = new Book();
        book.isbn = bookDto.getIsbn();
        book.title = bookDto.getTitle();
        book.author = bookDto.getAuthor();
        book.publisher = bookDto.getPublisher();
        book.description = bookDto.getDescription();
        book.price = bookDto.getPrice();
        book.image = bookDto.getImage();
        book.stock = 0;
        book.salesVolume = 0;
        return book;
    }


    public void plusStock() {
        this.stock += 1;
    }

    public void minusStock() {
        this.stock -= 1;
    }

    public void plusSalesVolume() {
        this.salesVolume += 1;
    }

    public void minusSalesVolume() {
        this.salesVolume -= 1;
    }
}
