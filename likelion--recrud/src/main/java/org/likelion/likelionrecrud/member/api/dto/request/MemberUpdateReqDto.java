package org.likelion.likelionrecrud.member.api.dto.request;

import org.likelion.likelionrecrud.member.application.validator.PartValid;
import org.likelion.likelionrecrud.member.domain.Part;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MemberUpdateReqDto(

		@NotEmpty(message = "member의 이름의 변경사항이 없으면 전에 이름을 입력해주세요.")
		@NotBlank(message = "member의 이름은 공백으로만 채울 수 없습니다.")
        String name,
        int age,
		@PartValid(enumClass = Part.class, allowLowerCase = true)
		String part
) {
}
