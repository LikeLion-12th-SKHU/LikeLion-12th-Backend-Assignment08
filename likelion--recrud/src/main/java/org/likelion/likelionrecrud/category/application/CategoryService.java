package org.likelion.likelionrecrud.category.application;

import java.util.List;
import java.util.stream.Collectors;

import org.likelion.likelionrecrud.category.api.dto.request.SaveCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.request.UpdateCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.likelion.likelionrecrud.exception.CustomException;
import org.likelion.likelionrecrud.exception.Error;
import org.likelion.likelionrecrud.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public CategoryInfoDto createCategory(SaveCategoryRequestDto requestDto) {
		Category parentCategory = (requestDto.parentId() != null) ?
			categoryRepository.findById(requestDto.parentId())
				.orElseThrow(
					() -> new NotFoundException(Error.CATEGORIES_NOT_FOUND_EXCEPTION,
						Error.CATEGORIES_NOT_FOUND_EXCEPTION.getMessage())) : null;
		Category category = Category.builder()
			.name(requestDto.categoryName())
			.parent(parentCategory)
			.build();
		categoryRepository.save(category);
		return CategoryInfoDto.from(category);
	}

	public CategoryListDto getAllCategory(int page, int size, String sort) {
		Pageable pageable;
		if(sort.isEmpty()){
			pageable = PageRequest.of(page,size, Sort.by("id").ascending());
		}else{
			String[] sortParams = sort.split(",");
			Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
			pageable = PageRequest.of(page,size,sortOrder);
		}
		try {
			Page<Category> categoryPage = categoryRepository.findAll(pageable);
			return CategoryListDto.from(
				categoryPage
					.stream()
					.map(CategoryInfoDto::from)
					.collect(Collectors.toList()));
		}catch (PropertyReferenceException e){	//정렬 속성등 잘못적었을 때
			throw new CustomException(Error.VALIDATION_ERROR, Error.PARAMETER_NOT_VALID_ERROR.getMessage());
		}
	}

	@Transactional
	public void updateCategory(Long categoryId, UpdateCategoryRequestDto requestDto) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new NotFoundException(Error.CATEGORIES_NOT_FOUND_EXCEPTION,Error.CATEGORIES_NOT_FOUND_EXCEPTION.getMessage()));
		Category parent = (requestDto.parentId() != null) ?
			categoryRepository.findById(requestDto.parentId())
				.orElseThrow(
					() -> new NotFoundException(Error.CATEGORIES_PARENT_NOT_FOUND_EXCEPTION,
						Error.CATEGORIES_PARENT_NOT_FOUND_EXCEPTION.getMessage())) : null;
		category.updateCategory(requestDto.name(), parent);
	}

	@Transactional
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new NotFoundException(Error.CATEGORIES_NOT_FOUND_EXCEPTION, Error.CATEGORIES_NOT_FOUND_EXCEPTION.getMessage()));
		category.getCategoryItems().forEach(
			categoryItem -> categoryItem.updateCategoryAndItem(null)
		);
		categoryRepository.delete(category);
	}
}
