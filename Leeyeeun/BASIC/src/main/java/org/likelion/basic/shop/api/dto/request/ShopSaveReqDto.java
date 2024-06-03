package org.likelion.basic.shop.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShopSaveReqDto(
        @NotNull(message = "작성자는 필수로 입력해야 합니다.")

        Long customerId,
        @NotBlank(message = "타이틀은 필수로 입력해야 합니다.")
        String title,
        @NotBlank(message = "내용은 필수로 입력해야 합니다.")

        String contents

) {
}
