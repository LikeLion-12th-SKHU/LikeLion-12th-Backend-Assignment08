package org.likelion.likelionassignmentcrud.order.api.dto.request;

public record OrderSaveReqDto(
        Long consumerId,
        Long price,
        String name
) {
}
