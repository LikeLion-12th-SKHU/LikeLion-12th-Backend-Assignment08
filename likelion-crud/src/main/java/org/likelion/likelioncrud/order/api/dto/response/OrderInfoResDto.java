package org.likelion.likelionassignmentcrud.order.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.order.domain.Order;

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
