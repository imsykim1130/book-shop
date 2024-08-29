package syk.study.bookshop.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // 기본 생성자 반드시 필요
// 임베디드 객체는 생성 이후 불변해야 하므로 setter 절대 쓰지 않는다
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Address {
    private String street;
    private String city;
}
