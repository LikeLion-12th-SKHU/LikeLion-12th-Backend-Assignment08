package org.likelion.likelionrecrud.member.api.dto.request;

import org.likelion.likelionrecrud.member.application.validator.PartValid;
import org.likelion.likelionrecrud.member.domain.Part;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MemberSaveReqDto (

		@NotEmpty(message = "member의 이름은 비어있을 수 없습니다.")
		@NotBlank(message = "member의 이름은 공백으로만 채울 수 없습니다.")
        String name,
		@Positive(message = "연나이를 입력해주세요.")
		@Min(value = 1, message = "1부터 100사이의 값만 입력해주세요.")
		@Max(value = 100, message = "1부터 100사이의 값만 입력해주세요.")
        int age,

		@PartValid(enumClass = Part.class, allowLowerCase = true)
        String part

){

}

