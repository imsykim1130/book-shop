package syk.study.bookshop.xml;

import lombok.*;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;
    String title;
    String link;
    String description;
    String lastBuildDate;
    String total;
    String start;
    String display;
    List<Item> item;
}
