package org.likelion.likelionassignmentcrud.shop.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ShopListResDto(
        List<ShopInfoResDto> posts
) {
    public static ShopListResDto from(List<ShopInfoResDto> shops) {
        return ShopListResDto.builder()
                .posts(shops)
                .build();
    }
}