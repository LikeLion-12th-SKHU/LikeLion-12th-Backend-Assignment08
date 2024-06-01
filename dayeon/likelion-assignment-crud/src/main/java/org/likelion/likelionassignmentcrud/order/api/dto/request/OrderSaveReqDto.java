package org.likelion.likelionassignmentcrud.order.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelionassignmentcrud.order.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.order.domain.Item;

public record OrderSaveReqDto(
        @NotNull(message = "사업자Id는 필수로 입력해야 합니다.")
        Long memberId,

        @NotBlank(message = "날짜는 필수로 입력해야 합니다.")
        Long date,

        @EnumValid(enumClass = Item.class)
        @NotBlank(message = "상품명은 필수로 입력해야 합니다.")
        Item item,

        @NotBlank(message = "수량은 필수로 입력해야 합니다.")
        @Positive(message = "잘못된 수량입니다.")
        int quantity
) {
}
