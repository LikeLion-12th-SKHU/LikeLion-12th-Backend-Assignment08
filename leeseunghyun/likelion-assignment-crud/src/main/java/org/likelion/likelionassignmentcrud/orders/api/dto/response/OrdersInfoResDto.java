package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;

@Builder
public record OrdersInfoResDto(
        String shippingAddress,
        String paymentInfo,

        Long foodId
) {
    public static OrdersInfoResDto from(Orders orders){
        return OrdersInfoResDto.builder()
                .shippingAddress(orders.getShippingAddress())
                .paymentInfo(orders.getPaymentInfo())
                .foodId(orders.getFood().getFoodId())
                .build();

    }
}