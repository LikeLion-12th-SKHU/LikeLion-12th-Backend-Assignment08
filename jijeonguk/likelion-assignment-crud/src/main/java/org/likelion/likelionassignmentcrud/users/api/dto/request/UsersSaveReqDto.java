package org.likelion.likelionassignmentcrud.users.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelionassignmentcrud.users.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.users.domain.PayOption;

public record UsersSaveReqDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 10, message = "2자 이상 10자 이하로 입력해주세요.")
        String name,

        @NotNull(message = "핸드폰 번호를 필수로 입력해야 합니다.")
        String phoneNumber,

        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$",
                message = "이메일 형식에 맞지 않습니다.")
        String email,

        @EnumValid(enumClass = PayOption.class)
        PayOption option
) {
}
