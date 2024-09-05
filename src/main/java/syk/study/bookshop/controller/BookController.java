package syk.study.bookshop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import syk.study.bookshop.dto.BookRegisterRequestDto;
import syk.study.bookshop.xml.Channel;
import syk.study.bookshop.xml.Item;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.service.BookApiService;
import syk.study.bookshop.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookApiService bookApiService;

    @GetMapping("/api/books")
    public ResponseEntity<?> bookSearchByTitle(@RequestParam(name = "title", required = false) String title,
                                                 @RequestParam(name = "display", required = false) String display,
                                                 @RequestParam(name = "start", required = false) String start,
                                                 @RequestParam(name = "isbn", required = false) String isbn) throws IOException {
        if(title != null && isbn == null) {
            Channel channel = bookApiService.getBooksByTitle(title, display, start);
            return ResponseEntity.ok(channel);
        } else if(title == null && isbn != null) {
            Item item = bookApiService.getBookByIsbn(isbn);
            return ResponseEntity.ok(item);
        } else {
            throw new RuntimeException("제목, isbn 둘 중 하나를 입력하세요.");
        }
    }


    @GetMapping("/api/books/top10")
    public ResponseEntity<?> bookSearchByTop10(){
        List<Book> response = bookService.getTop10Books();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/register/book")
    public ResponseEntity<?> registerBook(@RequestBody BookRegisterRequestDto request) throws IOException{
        bookService.registerBook(request);
        return ResponseEntity.ok(request);
    }



}
