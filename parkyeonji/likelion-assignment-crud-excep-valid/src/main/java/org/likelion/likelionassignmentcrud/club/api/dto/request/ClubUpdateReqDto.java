package org.likelion.likelionassignmentcrud.club.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClubUpdateReqDto(
        @NotBlank(message = "동아리 이름을 필수로 입력해야 합니다.")
        String name,

        @NotBlank(message = "동아리 유형을 필수로 입력해야 합니다.")
        String clubType
) {
}
