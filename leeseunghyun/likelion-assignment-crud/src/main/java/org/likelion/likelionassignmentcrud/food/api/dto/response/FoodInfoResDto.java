package org.likelion.likelionassignmentcrud.food.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.food.domain.Type;

@Builder
public record FoodInfoResDto(
        String name,
        int price,
        //String type,
        int foodPassword,

        Type type
) {
    public static FoodInfoResDto from(Food food) {
        return FoodInfoResDto.builder()
                .name(food.getName())
                .price(food.getPrice())
                .foodPassword(food.getFoodPassword())
                .type(food.getType())
                .build();
    }
}
