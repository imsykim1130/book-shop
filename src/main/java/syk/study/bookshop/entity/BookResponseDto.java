package syk.study.bookshop.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class BookResponseDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Object> items;
}
