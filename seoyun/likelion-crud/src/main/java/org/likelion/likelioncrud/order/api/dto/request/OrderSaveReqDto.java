package org.likelion.likelioncrud.order.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record OrderSaveReqDto(
        @NotNull(message = "고객을 필수로 입력해야 합니다.")
        Long consumerId,
        @NotNull(message = "가격을 필수로 입력해야 합니다.")
        Long price,
        @NotNull(message = "이름을 필수로 입력해야 합니다.")
        String name
) {
}
