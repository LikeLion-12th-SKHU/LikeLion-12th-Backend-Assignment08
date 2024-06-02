package org.likelion.likelionassignmentcrud.food.api;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodSaveReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodInfoResDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodListResDto;
import org.likelion.likelionassignmentcrud.food.application.FoodService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<FoodInfoResDto> foodSave(@RequestBody @Valid FoodSaveReqDto foodSaveReqDto) {
        FoodInfoResDto foodInfoResDto = foodService.foodSave(foodSaveReqDto);
        return BaseResponse.success(SuccessCode.FOOD_SAVE_SUCCESS, foodInfoResDto);
    }

    @GetMapping("/foods")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FoodListResDto> foodFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "memberId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("fooodId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        FoodListResDto foodListResDto = foodService.foodFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, foodListResDto);
    }

    @GetMapping("/{foodId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FoodInfoResDto> foodFindOne(@PathVariable("foodId") Long foodId) {
        FoodInfoResDto foodInfoResDto = foodService.foodFindOne(foodId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, foodInfoResDto);

    }

    @PatchMapping("/{foodId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FoodInfoResDto> foodUpdate(@PathVariable("foodId") Long foodId,
                                                                   @RequestBody @Valid FoodUpdateReqDto foodUpdateReqDto) {
        FoodInfoResDto foodInfoResDto = foodService.foodUpdate(foodId, foodUpdateReqDto);
        return BaseResponse.success(SuccessCode.FOOD_UPDATE_SUCCESS, foodInfoResDto);
    }

    @DeleteMapping("/{foodId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FoodInfoResDto> foodDelete(@PathVariable("foodId") Long foodId) {
        FoodInfoResDto foodInfoResDto = foodService.foodDelete(foodId);
        return BaseResponse.success(SuccessCode.FOOD_DELETE_SUCCESS, foodInfoResDto);
    }
}
