package syk.study.bookshop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import syk.study.bookshop.dto.*;
import syk.study.bookshop.service.BookApiService;
import syk.study.bookshop.service.BookService;
import syk.study.bookshop.service.ForSaleBookService;
import syk.study.bookshop.service.ShoppingCartBookService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookApiService bookApiService;
    private final ForSaleBookService forSaleBookService;
    private final ShoppingCartBookService shoppingCartBookService;

    // nave api 에서 책 검색 결과 가져오기
    // title, isbn 둘 중 하나만 입력되어야함
    // title 로 검색할 때는 display(가져올 데이터 개수), start(데이터 표시 시작 위치) 가 필요
    @GetMapping("/api/books")
    public ResponseEntity<?> bookSearchByTitle(@RequestParam(name = "title", required = false) String title,
                                                 @RequestParam(name = "display", required = false) String display,
                                                 @RequestParam(name = "start", required = false) String start,
                                                 @RequestParam(name = "isbn", required = false) String isbn) throws IOException {
        // title 로 검색
        if(title != null && isbn == null) {
            BookSearchResponseDto bookSearchResponseDto = bookApiService.getBooksByTitle(title, display, start);
            return ResponseEntity.ok(bookSearchResponseDto);
        // isbn 으로 검색
        } else if(title == null && isbn != null) {
            BookDto bookByIsbn = bookApiService.getBookByIsbn(isbn);
            return ResponseEntity.ok(bookByIsbn);
        // 🥕 커스텀 예외로 추후 변경 필요
        } else {
            throw new RuntimeException("제목, isbn 둘 중 하나를 입력하세요.");
        }
    }

    // 판매량 순으로 책 10개 가져오기
    @GetMapping("/api/books/top10")
    public ResponseEntity<List<BookDto>> bookSearchByTop10(){
        List<BookDto> response = bookService.getTop10Books();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/api/register/book")
    public ResponseEntity<?> registerBook(@RequestBody BookRegisterRequestDto request) throws IOException{
        bookService.registerBook(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/api/for-sales-books")
    public ResponseEntity<List<ForSalesBookDto>> forSalesBooks(@RequestParam(name = "isbn") String isbn){
        List<ForSalesBookDto> forSalesBooksDto = forSaleBookService.getForSalesBooks(isbn).stream().filter(book -> book.isSold() == false).toList();
        return ResponseEntity.ok(forSalesBooksDto);
    }

    @PostMapping("/api/shopping-cart/register")
    public ResponseEntity<?> registerBookToshoppingCart(@RequestBody ShoppingCartRequestDto request){
        shoppingCartBookService.addBookToShoppingCart(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/api/shopping-cart")
    public ResponseEntity<?> getShoppingCart(@RequestParam(name = "username") String username){
        List<ForSalesBookDto> booksFromShoppingCart = shoppingCartBookService.getBookFromShoppingCart(username);
        return ResponseEntity.ok(booksFromShoppingCart);
    }
}
