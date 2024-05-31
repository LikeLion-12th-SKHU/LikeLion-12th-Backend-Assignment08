package org.likelion.likelionassignmentcrud.developer.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record DeveloperSaveReqDto(
        // 이름, 국가, 설립일

        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 1, max = 20, message = "1자 이상 20자 이하로 입력해주세요.")
        String name,

        @NotBlank(message = "국가는 필수로 입력해야 합니다.")
        @Size(min = 1, max = 20, message = "1자 이상 20자 이하로 입력해주세요.")
        String country,

        @NotNull(message = "설립일은 비어있을 수 없습니다.")
        @PastOrPresent(message = "설립일은 과거 또는 현재 날짜여야 합니다.")
        LocalDate establishedDate
) {
}
