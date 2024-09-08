package syk.study.bookshop.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoppingCartRequestDto {
    private Long forSalesBookId;
    private String sellerName;
}
