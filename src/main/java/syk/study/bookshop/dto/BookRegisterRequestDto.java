package syk.study.bookshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import syk.study.bookshop.entity.BookCondition;
import syk.study.bookshop.xml.Item;

@NoArgsConstructor
@Getter
public class BookRegisterRequestDto {
    private String username;
    private BookCondition condition;
    private BookDto bookDto;
}
