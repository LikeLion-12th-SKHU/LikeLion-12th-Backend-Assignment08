package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;

@Builder
public record OrdersInfoResDto(
        String address,
        Long price,
        String writer
) {

    public static OrdersInfoResDto from(Orders orders){
        return OrdersInfoResDto.builder()
                .address(orders.getAddress())
                .price(orders.getPrice())
                .writer(orders.getUsers().getName())
                .build();
    }
}
