package syk.study.bookshop.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import syk.study.bookshop.dto.BookDto;

import java.io.Serializable;

@Getter
@Setter
@ToString
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    String title;
    String link;
    String image;
    String author;
    int discount;
    String publisher;
    String pubdate;
    String isbn;
    String description;
}
