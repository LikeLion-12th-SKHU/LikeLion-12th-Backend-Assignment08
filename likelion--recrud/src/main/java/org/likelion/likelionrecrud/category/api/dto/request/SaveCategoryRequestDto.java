package org.likelion.likelionrecrud.category.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SaveCategoryRequestDto(
	@NotBlank(message = "카테고리 이름은 공백으로만 채울 수 없습니다.")
	@NotEmpty(message = "카테고리 이름은 비어있으면 안됩니다.")
	String categoryName,
	Long parentId) {
}
