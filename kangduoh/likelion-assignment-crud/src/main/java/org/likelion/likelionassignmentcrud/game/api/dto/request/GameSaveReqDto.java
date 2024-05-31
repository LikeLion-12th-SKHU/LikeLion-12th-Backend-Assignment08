package org.likelion.likelionassignmentcrud.game.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.likelion.likelionassignmentcrud.game.api.validator.EnumValid;
import org.likelion.likelionassignmentcrud.game.domain.Platform;

public record GameSaveReqDto(
        // 개발사, 게임명, 장르, 플랫폼

        @NotNull(message = "개발사를 필수로 입력해야 합니다.")
        Long developerId,

        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        String name,

        @NotBlank(message = "장르를 필수로 입력해야 합니다.")
        String genre,

        @EnumValid(enumClass = Platform.class)
        Platform platform
) {
}
