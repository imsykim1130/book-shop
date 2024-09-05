package syk.study.bookshop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ForSalesBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private BookCondition condition;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isSold;

    private LocalDateTime registerDate;


    public ForSalesBook(Book book, BookCondition condition, User user) {
        this.book = book;
        this.condition = condition;
        this.user = user;
        this.price = (int) Math.floor(this.condition.getDiscount() * book.getPrice());
        this.isSold = false;
        this.registerDate = LocalDateTime.now();
    }
}
