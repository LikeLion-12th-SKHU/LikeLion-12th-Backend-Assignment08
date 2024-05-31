package org.likelion.likelionassignmentcrud.developer.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeveloperUpdateReqDto(
        @NotBlank(message = "국가는 필수로 입력해야 합니다.")
        @Size(min = 1, max = 20, message = "1자 이상 20자 이하로 입력해주세요.")
        String country
) {
}
