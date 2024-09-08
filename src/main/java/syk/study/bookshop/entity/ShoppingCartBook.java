package syk.study.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ShoppingCartBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "forSaleBook_id")
    private ForSalesBook forSalesBook;

    public ShoppingCartBook(User user, ForSalesBook forSaleBook) {
        this.user = user;
        this.forSalesBook = forSaleBook;
    }

    public void setShoppingCartBook(User user, ForSalesBook forSaleBook) {
        this.user = user;
        this.forSalesBook = forSaleBook;
    }
}
