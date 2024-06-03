package org.likelion.likelionassignmentcrud.major.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MajorSaveReqDto(
        @NotNull(message = "학번은 필수로 입력해야합니다.")
        Long studentId,

        @NotBlank(message = "이름은 필수로 입력해야합니다.")
        String name,

        @NotBlank(message = "파트는 필수로 입력해야합니다.")
        String part
) {
}
