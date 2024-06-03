package org.likelion.basic.shop.api.dto.response;

import lombok.Builder;
import org.likelion.basic.shop.domain.Shop;

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
