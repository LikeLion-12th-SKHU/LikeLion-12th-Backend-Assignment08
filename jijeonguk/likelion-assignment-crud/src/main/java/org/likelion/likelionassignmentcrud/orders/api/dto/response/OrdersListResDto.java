package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import lombok.Builder;

import java.util.List;
@Builder

public record OrdersListResDto(
        List<OrdersInfoResDto> ordersInfoResDtoList
) {
    public static OrdersListResDto from(List<OrdersInfoResDto> ordersInfoResDtoList){
        return OrdersListResDto.builder()
                .ordersInfoResDtoList(ordersInfoResDtoList)
                .build();
    }
}
