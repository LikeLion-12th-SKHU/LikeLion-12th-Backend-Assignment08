package org.likelion.likelionassignmentcrud.users.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsersUpdateReqDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 10, message = "2자 이상 10자 이하로 입력해주세요.")
        String name,

        @NotBlank(message = "핸드폰 번호를 필수로 입력해야 합니다.")
        @NotNull(message = "핸드폰 번호를 필수로 입력해야 합니다.")
        String phoneNumber,

        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$",
                message = "이메일 형식에 맞지 않습니다.")
        String email
) {
}
