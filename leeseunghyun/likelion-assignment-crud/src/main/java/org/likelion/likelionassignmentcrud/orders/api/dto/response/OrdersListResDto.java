package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import java.util.List;
import lombok.Builder;


@Builder
public record OrdersListResDto(
        List<OrdersInfoResDto>  orderss
) {
    public static OrdersListResDto from(List<OrdersInfoResDto> orderss) {
        return OrdersListResDto.builder()
                .orderss(orderss)
                .build();

    }
}