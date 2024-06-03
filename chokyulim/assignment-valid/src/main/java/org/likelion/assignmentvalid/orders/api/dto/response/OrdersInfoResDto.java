package org.likelion.assignmentvalid.orders.api.dto.response;

import lombok.Builder;
import org.likelion.assignmentvalid.orders.domain.Orders;

@Builder
public record OrdersInfoResDto(
        String custName,
        String location
) {
    public static OrdersInfoResDto from(Orders orders) {
        return OrdersInfoResDto.builder()
                .custName(orders.getCustName())
                .location(orders.getLocation())
                .build();
    }
}
