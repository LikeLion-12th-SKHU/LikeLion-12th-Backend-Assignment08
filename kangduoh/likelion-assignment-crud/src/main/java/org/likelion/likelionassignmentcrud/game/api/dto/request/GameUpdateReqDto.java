package org.likelion.likelionassignmentcrud.game.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.likelion.likelionassignmentcrud.game.api.validator.EnumValid;
import org.likelion.likelionassignmentcrud.game.domain.Platform;

public record GameUpdateReqDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        String name,

        @NotBlank(message = "장르를 필수로 입력해야 합니다.")
        String genre,

        @EnumValid(enumClass = Platform.class)
        Platform platform
) {
}
