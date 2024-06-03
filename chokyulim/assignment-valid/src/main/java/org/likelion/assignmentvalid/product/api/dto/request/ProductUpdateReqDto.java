package org.likelion.assignmentvalid.product.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductUpdateReqDto(
        @NotBlank(message = "상품 이름을 필수로 입력해야 합니다.")
        @Size(min = 1, max = 10, message = "1자 이상 10자 이하로 입력하세요.")
        String name,

        @NotNull(message = "가격을 필수로 입력해야 합니다.")
        int price
) {
}
