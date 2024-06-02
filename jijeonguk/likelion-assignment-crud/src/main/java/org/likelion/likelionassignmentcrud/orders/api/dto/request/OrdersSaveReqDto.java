package org.likelion.likelionassignmentcrud.orders.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdersSaveReqDto(
        //not null은 빈칸을 허용하지 않음
        @NotNull(message = "고객을 필수로 입력해야 합니다.")
        Long usersId,
        @NotBlank(message = "주소를 필수로 입력해야 합니다")
        String address,
        @NotBlank(message = "가격을 필수로 입력해야 합니다.")
        Long price
) {
}
