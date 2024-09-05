package syk.study.bookshop.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.dto.BookRegisterRequestDto;
import syk.study.bookshop.xml.Item;
import syk.study.bookshop.entity.Book;
import syk.study.bookshop.entity.ForSalesBook;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.BookRepository;
import syk.study.bookshop.repository.ForSalesBookRepository;
import syk.study.bookshop.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ForSalesBookRepository forSalesBookRepository;
    private final BookApiService bookApiService;

    public void registerBook(BookRegisterRequestDto request) throws IOException {
        String username = request.getUsername();
        User resultUser;
        Book resultBook;

        boolean isBookExist = bookRepository.existsById(request.getItem().getIsbn());

        if (isBookExist) {
            resultBook = bookRepository.findById(request.getItem().getIsbn()).get();
        } else {
            resultBook = bookRepository.save(Book.makeNewBook(request.getItem()));
        }

        resultBook.plusStock();

        // username 으로 사용자 정보 얻기
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }

        resultUser = user.get();

        ForSalesBook forSalesBook = new ForSalesBook(resultBook, request.getCondition(), resultUser);
        forSalesBookRepository.save(forSalesBook);
    }

    // 판매 순위 10위까지 책 가져오기
    public List<Book> getTop10Books() {
        Sort sortBy = Sort.by(Sort.Direction.DESC, "salesVolume");
        return bookRepository.findAll(sortBy);
    }

    // isbn 으로 책 검색(책 하나)
    public Book getBookByIsbn(String isbn) throws IOException {
        Item item = bookApiService.getBookByIsbn(isbn);
        return Book.makeNewBook(item);
    }
}
