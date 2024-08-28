package syk.study.bookshop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import syk.study.bookshop.entity.BookRequestDto;
import syk.study.bookshop.entity.BookResponseDto;
import syk.study.bookshop.service.BookService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/t")
    public ResponseEntity<BookResponseDto> bookSearchByTitle(@RequestParam(name="title") String title) throws IOException {
        // naver api 에서 데이터 받아오기
        BookResponseDto result = bookService.getData(title, "titl");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/books/is")
    public ResponseEntity<BookResponseDto> bookSearchByIsbn(@RequestParam(name="isbn") String isbn) throws IOException {
        // naver api 에서 데이터 받아오기
        BookResponseDto result = bookService.getData(isbn, "isbn");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/shopping-cart")
    public ResponseEntity<?> addBook(@RequestBody BookRequestDto bookRequestDto) {
//        bookService.registerBook(bookRequestDto);
        return ResponseEntity.ok(bookRequestDto);
    }



}
