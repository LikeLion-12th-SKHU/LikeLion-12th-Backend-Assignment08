package org.likelion.likelionassignmentcrud.shop.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.shop.domain.Shop;

@Builder
public record ShopInfoResDto(
        String title,
        String contents,
        String writer
) {
    public static ShopInfoResDto from(Shop shop) {
        return ShopInfoResDto.builder()
                .title(shop.getTitle())
                .contents(shop.getContents())
                .writer(shop.getCustomer().getName())
                .build();
    }
}
