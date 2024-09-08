package syk.study.bookshop.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookSearchResponseDto {
    private int total;
    private List<BookDto> books;
}
