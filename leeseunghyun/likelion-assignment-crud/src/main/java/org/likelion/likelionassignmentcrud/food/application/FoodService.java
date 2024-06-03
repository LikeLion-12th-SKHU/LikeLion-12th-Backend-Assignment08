package org.likelion.likelionassignmentcrud.food.application;

import java.util.List;
import java.util.stream.Collectors;

import org.likelion.likelionassignmentcrud.common.exception.CustomException;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodSaveReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodListResDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodInfoResDto;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.food.domain.repository.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // Create
    @Transactional
    public FoodInfoResDto foodSave(FoodSaveReqDto foodSaveReqDto) {
        Food food = Food.builder()
                .name(foodSaveReqDto.name())
                .price(foodSaveReqDto.price())
                .foodPassword(foodSaveReqDto.foodPassword())
                .type(foodSaveReqDto.type())
                .build();

        foodRepository.save(food);
        return FoodInfoResDto.from(food);
    }

    // Read All
    public FoodListResDto foodFindAll(Pageable pageable) {
        Page<Food> foods = foodRepository.findAll(pageable);

        List<FoodInfoResDto> foodInfoResDtoList = foods.stream()
                .map(FoodInfoResDto::from)
                .collect(Collectors.toList());
        return FoodListResDto.from(foodInfoResDtoList);
    }

    // Read One
    public FoodInfoResDto foodFindOne(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(ErrorCode.FOODS_NOT_FOUND_EXCEPTION, ErrorCode.FOODS_NOT_FOUND_EXCEPTION.getMessage() + foodId));

        return FoodInfoResDto.from(food);
    }

    @Transactional
    public FoodInfoResDto foodUpdate(Long foodId, FoodUpdateReqDto foodUpdateReqDto) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(ErrorCode.FOODS_NOT_FOUND_EXCEPTION, ErrorCode.FOODS_NOT_FOUND_EXCEPTION.getMessage() + foodId));

        food.update(foodUpdateReqDto);
        return FoodInfoResDto.from(food);
    }

    // Delete
    @Transactional
    public FoodInfoResDto foodDelete(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(ErrorCode.FOODS_NOT_FOUND_EXCEPTION, ErrorCode.FOODS_NOT_FOUND_EXCEPTION.getMessage() + foodId));

        foodRepository.delete(food);
        return FoodInfoResDto.from(food);
    }
}
