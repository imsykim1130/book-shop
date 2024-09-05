package syk.study.bookshop.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Rss implements Serializable {
    private static final long serialVersionUID = 1L;
    private Channel channel;
}
