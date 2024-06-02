package org.likelion.jangsu1.Student.api.request;

import jakarta.validation.constraints.*;
import org.likelion.jangsu1.Student.application.validator.EnumValid;
import org.likelion.jangsu1.Student.domain.Part;

public record StudentUpdateReqDto(

        @NotBlank(message = "이름을 필수로 입력하여 주십시오.")
        @Size(min = 2, max = 15, message = "2자 이상 15자 이하로 입력하시오.")
        String name,

        @NotNull(message = "나이를 필수로 입력해 주십시오.")
        @PositiveOrZero(message = "0보다 작은 값은 입력할 수 없습니다.")
        int age,

        @NotBlank(message = "이메일을 필수로 입력하여 주십시오")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        String email,

        @EnumValid(enumClass = Part.class)
        Part part
) {
}
