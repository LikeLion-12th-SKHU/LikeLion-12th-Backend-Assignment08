package org.likelion.likelionassignmentcrud.orders.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdersUpdateReqDto(
        @NotBlank(message = "주소를 필수로 입력해야 합니다")
        String address,
        @NotNull(message = "가격을 필수로 입력해야 합니다.")
        Long price
) {
}
