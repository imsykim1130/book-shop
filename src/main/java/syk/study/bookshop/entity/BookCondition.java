package syk.study.bookshop.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookCondition {
    HIGH("상", 0.7),
    MIDDLE("중", 0.5),
    LOW("하", 0.3);

    private final String condition;
    private final double discount;
}
