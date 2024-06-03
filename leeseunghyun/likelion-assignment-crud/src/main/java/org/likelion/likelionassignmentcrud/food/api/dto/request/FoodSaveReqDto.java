package org.likelion.likelionassignmentcrud.food.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.likelionassignmentcrud.food.application.validator.EnumValid;
import org.likelion.likelionassignmentcrud.food.domain.Type;
import org.springframework.data.repository.query.parser.Part;

public record FoodSaveReqDto<part>(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 15, message = "2자 이상 15자 이하로 입력해주세요.")
        String name,

        @Positive(message = "가격은 양수로 입력해주세요.")
        @Min(value = 10000, message = "가격은 최소 1만원 이상이어야 합니다.")
        @Max(value = 50000, message = "가격은 최대 5만원 이하여야 합니다.")
        int price,
        @EnumValid(enumClass = Type.class, message = "유효한 유형을 선택해주세요.")
        Type type,


        @Min(value = 0, message = "비밀번호는 양수로 입력해주세요.")
        @Max(value = 99999999, message = "비밀번호는 8자리 이하의 숫자로 입력해주세요.")
        int foodPassword


) {
}
