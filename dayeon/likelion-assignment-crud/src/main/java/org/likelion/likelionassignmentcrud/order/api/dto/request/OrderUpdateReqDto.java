package org.likelion.likelionassignmentcrud.order.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelionassignmentcrud.order.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.order.domain.Item;

public record OrderUpdateReqDto(
        @NotNull(message = "날짜는 필수로 입력해야 합니다.")
        @Max(value = 6, message = "YYYYMMDD 형식으로 입력해주세요")
        int date,

        @EnumValid(enumClass = Item.class)
        @NotNull(message = "상품명은 필수로 입력해야 합니다.")
        Item item,

        @NotNull(message = "수량은 필수로 입력해야 합니다.")
        @Positive(message = "잘못된 수량입니다.")
        int quantity
) {
}
