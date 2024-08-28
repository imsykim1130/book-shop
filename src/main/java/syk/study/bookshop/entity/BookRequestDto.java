package syk.study.bookshop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRequestDto {
    private String username;
    private String isbn;
    private String condition;
    private String title;
    private String post;
}
