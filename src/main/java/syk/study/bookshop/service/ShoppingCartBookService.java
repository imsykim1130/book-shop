package syk.study.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syk.study.bookshop.dto.ForSalesBookDto;
import syk.study.bookshop.dto.ShoppingCartRequestDto;
import syk.study.bookshop.entity.ForSalesBook;
import syk.study.bookshop.entity.ShoppingCartBook;
import syk.study.bookshop.entity.User;
import syk.study.bookshop.repository.ForSalesBookRepository;
import syk.study.bookshop.repository.ShoppingCartBookRepository;
import syk.study.bookshop.repository.UserRepository;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartBookService {
    private final ShoppingCartBookRepository shoppingCartBookRepository;
    private final ForSalesBookRepository forSalesBookRepository;
    private final UserRepository userRepository;

    public void addBookToShoppingCart(ShoppingCartRequestDto request) {
        User user = userRepository.findByUsername(request.getSellerName()).orElseThrow();
        ForSalesBook forSalesBook = forSalesBookRepository.findById(request.getForSalesBookId()).orElseThrow();
        if(!shoppingCartBookRepository.existsByForSalesBook(forSalesBook)) {
            ShoppingCartBook shoppingCartBook = new ShoppingCartBook(user, forSalesBook);
            shoppingCartBookRepository.save(shoppingCartBook);
        }
    }

    public List<ForSalesBookDto> getBookFromShoppingCart(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return shoppingCartBookRepository.findByUser(user).stream().map(book -> ForSalesBookDto.createNewForSalesBookDto(book.getForSalesBook())).toList();
    }
}
