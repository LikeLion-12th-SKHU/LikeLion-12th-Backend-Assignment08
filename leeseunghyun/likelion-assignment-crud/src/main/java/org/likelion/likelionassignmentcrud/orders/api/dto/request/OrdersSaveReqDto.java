package org.likelion.likelionassignmentcrud.orders.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdersSaveReqDto(

        @NotBlank(message = "주소를 필수로 입력해야합니다.")
        String shippingAddress,
        @NotBlank(message = "결제수단을 필수로 입력해야합니다.")
        String paymentInfo,
        @NotNull(message = "음식을 필수로 입력해야합니다.")
        Long foodId
) {
}