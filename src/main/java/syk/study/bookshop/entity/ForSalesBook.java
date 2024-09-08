package syk.study.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "forSaleBook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private BookCondition condition;

    private int originPrice;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User seller;

    private boolean isSold;

    private LocalDateTime registerDate;


    public ForSalesBook(Book book, BookCondition condition, User seller) {
        this.book = book;
        this.condition = condition;
        this.seller = seller;
        this.originPrice = book.getPrice();
        this.price = (int) Math.floor(this.condition.getDiscount() * book.getPrice());
        this.isSold = false;
        this.registerDate = LocalDateTime.now();
    }

}
