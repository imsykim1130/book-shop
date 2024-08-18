package syk.study.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syk.study.bookshop.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
