package org.likelion.likelionrecrud.category.api.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequestDto(
	@NotBlank(message = "카테고리 이름은 공백으로만 채울 수 없습니다.")
	@NotEmpty(message = "카테고리 이름은 비어있으면 안됩니다.")
	@Length(min = 2, max=15, message = "두 글자 이상으로 설정해주세요.")
	String name,
	Long parentId) {
}
