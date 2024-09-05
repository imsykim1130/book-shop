package syk.study.bookshop.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAll(Sort sortBy);
}
