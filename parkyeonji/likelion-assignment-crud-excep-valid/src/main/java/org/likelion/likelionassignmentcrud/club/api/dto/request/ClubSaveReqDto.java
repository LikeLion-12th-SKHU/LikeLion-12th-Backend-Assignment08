package org.likelion.likelionassignmentcrud.club.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.likelion.likelionassignmentcrud.club.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.club.domain.Part; // 수정

public record ClubSaveReqDto(
        @NotBlank(message = "동아리 이름을 필수로 입력해야 합니다.")
        String name,

        @NotBlank(message = "동아리 유형을 필수로 입력해야 합니다.")
        String clubType,

        @EnumValid(enumClass = Part.class)
        Part part
) {
}
