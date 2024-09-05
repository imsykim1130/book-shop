package syk.study.bookshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.entity.ForSalesBook;

import java.util.List;

public interface ForSalesBookRepository extends JpaRepository<ForSalesBook, Long> {
    List<ForSalesBook> findByBook(Book book);
}
