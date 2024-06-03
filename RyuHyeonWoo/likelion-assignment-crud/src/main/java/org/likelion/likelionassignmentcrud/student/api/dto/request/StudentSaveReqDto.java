package org.likelion.likelionassignmentcrud.student.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelionassignmentcrud.student.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;

public record StudentSaveReqDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 10, message = "2자 이상 10자 이하로 입력해주세요.")
        String name,

        @NotNull(message = "나이를 필수로 입력해야 합니다.")
        @Positive(message = "연나이를 입력해주세요.")
        @Max(value = 100, message = "1부터 100사이의 값만 입력할 수 있습니다.")
        int age,

        Long studentId,

        @EnumValid(enumClass = StudentGrade.class)
        StudentGrade stuGrade
) {

}
