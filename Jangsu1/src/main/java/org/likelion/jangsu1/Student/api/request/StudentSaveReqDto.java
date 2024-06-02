package org.likelion.jangsu1.Student.api.request;

import jakarta.validation.constraints.*;
import org.likelion.jangsu1.Student.application.validator.EnumValid;
import org.likelion.jangsu1.Student.domain.Part;

public record StudentSaveReqDto(
        // 반드시 값이 존재하고 공백문자를 제외한 길이가 0보다 커야 한다.
        @NotBlank(message = "이름을 필수로 입력하여 주십시오.")
        // 크기를 제한할 때 사용하며, min, max 를 통해 최소 크기, 최대 크기를 제한할 수 있음.
        @Size(min = 2, max = 15, message = "2자 이상 15자 이하로 입력하시오.")
        String name,

        // 반드시 값이 있어야 한다.
        @NotNull(message = "나이를 필수로 입력해 주십시오.")
        @PositiveOrZero(message = "0보다 작은 값은 입력할 수 없습니다.")
        int age,

        @NotBlank(message = "이메일을 필수로 입력하여 주십시오")
        // 정규식을 분석
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        String email,

        @EnumValid(enumClass = Part.class)
        Part part
) {
}
