package org.likelion.likelionassignmentcrud.order.api.dto.request;

public record OrderUpdateReqDto(
        Long price,
        String name
) {
}
