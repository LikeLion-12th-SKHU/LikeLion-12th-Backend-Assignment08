package org.likelion.assignmentvalid.orders.api.dto.response;

import lombok.Builder;
import org.likelion.assignmentvalid.orders.api.dto.response.OrdersInfoResDto;

import java.util.List;

@Builder
public record OrdersListResDto(
        List<OrdersInfoResDto>  ordersList
) {
    public static OrdersListResDto from(List<OrdersInfoResDto> ordersList) {
        return OrdersListResDto.builder()
                .ordersList(ordersList)
                .build();
    }
}
