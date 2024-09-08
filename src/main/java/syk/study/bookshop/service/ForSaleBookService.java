package syk.study.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.dto.ForSalesBookDto;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.repository.BookRepository;
import syk.study.bookshop.repository.ForSalesBookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ForSaleBookService {
    private final ForSalesBookRepository forSalesBookRepository;
    private final BookRepository bookRepository;

    public List<ForSalesBookDto> getForSalesBooks(String isbn) {
        // book 찾기
        Book book = bookRepository.findById(isbn).orElseThrow();
        return forSalesBookRepository.findByBook(book).stream().map(forSalesBook -> ForSalesBookDto.createNewForSalesBookDto(forSalesBook)).toList();
    }
}
