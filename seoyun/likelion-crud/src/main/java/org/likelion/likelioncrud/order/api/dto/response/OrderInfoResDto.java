package org.likelion.likelioncrud.order.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrud.order.domain.Order;

@Builder
public record OrderInfoResDto(
        Long price,
        String name,
        String seller
) {
    public static OrderInfoResDto from(Order order) {
        return OrderInfoResDto.builder()
                .price(order.getPrice())
                .name(order.getName())
                .seller(order.getConsumer().getName())
                .build();
    }
}