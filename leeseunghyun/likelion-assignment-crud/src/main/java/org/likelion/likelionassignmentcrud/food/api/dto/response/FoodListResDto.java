package org.likelion.likelionassignmentcrud.food.api.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record FoodListResDto(
        List<FoodInfoResDto> foods
) {
    public static FoodListResDto from(List<FoodInfoResDto> foods) {
        return FoodListResDto.builder()
                .foods(foods)
                .build();
    }
}
