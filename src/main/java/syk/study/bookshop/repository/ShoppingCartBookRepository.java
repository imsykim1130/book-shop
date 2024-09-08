package syk.study.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.ForSalesBook;
import syk.study.bookshop.entity.ShoppingCartBook;
import syk.study.bookshop.entity.User;

import java.util.List;

public interface ShoppingCartBookRepository extends JpaRepository<ShoppingCartBook, Long> {
    List<ShoppingCartBook> findByUser(User user);

    boolean existsByForSalesBook(ForSalesBook forSalesBook);
}
