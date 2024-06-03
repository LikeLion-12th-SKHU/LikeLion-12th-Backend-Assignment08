package org.likelion.assignmentvalid.orders.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrdersSaveReqDto(
        // 사용자 아이디, 제목, 내용

        Long productId,

        @NotBlank(message = "고객 이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 5, message = "2자 이상 5자 이하로 입력하세요.")
        String custName,

        @NotNull(message = "지역을 필수로 입력해야 합니다.")
        String location
) {
}
