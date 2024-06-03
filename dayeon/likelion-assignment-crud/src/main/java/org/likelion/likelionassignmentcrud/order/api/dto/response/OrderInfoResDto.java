package org.likelion.likelionassignmentcrud.order.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.order.domain.Item;
import org.likelion.likelionassignmentcrud.order.domain.Order;

@Builder
public record OrderInfoResDto(
        int date,
        Item item,
        int quantity,
        String member
) {
    public static OrderInfoResDto from(Order order) {
        return OrderInfoResDto.builder()
                .date(order.getDate())
                .item(order.getItem())
                .quantity(order.getQuantity())
                .member(order.getMember().getName())
                .build();
    }
}
