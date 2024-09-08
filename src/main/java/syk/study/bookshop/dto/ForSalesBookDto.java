package syk.study.bookshop.dto;

import lombok.Getter;
import syk.study.bookshop.entity.BookCondition;
import syk.study.bookshop.entity.ForSalesBook;
import java.time.LocalDateTime;

@Getter
public class ForSalesBookDto {
    private Long id;
    private String isbn;
    private String title;
    private String description;
    private String image;
    private String publisher;
    private String author;
    private String sellerName;
    private BookCondition condition;
    private int originPrice;
    private int price;
    private LocalDateTime registerDate;
    private boolean isSold;

    public static ForSalesBookDto createNewForSalesBookDto(ForSalesBook forSalesBook) {
        ForSalesBookDto forSalesBookDto = new ForSalesBookDto();
        forSalesBookDto.id = forSalesBook.getId();
        forSalesBookDto.isbn = forSalesBook.getBook().getIsbn();
        forSalesBookDto.title = forSalesBook.getBook().getTitle();
        forSalesBookDto.description = forSalesBook.getBook().getDescription();
        forSalesBookDto.image = forSalesBook.getBook().getImage();
        forSalesBookDto.publisher = forSalesBook.getBook().getPublisher();
        forSalesBookDto.author = forSalesBook.getBook().getAuthor();
        forSalesBookDto.sellerName = forSalesBook.getSeller().getUsername();
        forSalesBookDto.condition = forSalesBook.getCondition();
        forSalesBookDto.originPrice = forSalesBook.getOriginPrice();
        forSalesBookDto.price = forSalesBook.getPrice();
        forSalesBookDto.registerDate = forSalesBook.getRegisterDate();
        forSalesBookDto.isSold = forSalesBook.isSold();
        return forSalesBookDto;
    }
}
