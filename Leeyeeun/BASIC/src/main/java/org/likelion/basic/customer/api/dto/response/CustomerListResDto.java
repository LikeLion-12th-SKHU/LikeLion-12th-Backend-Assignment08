package org.likelion.likelionassignmentcrud.customer.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerListResDto(
        List<CustomerInfoResDto> customers

) {

    public static CustomerListResDto from(List<CustomerInfoResDto> customers) {
        return CustomerListResDto.builder()
                .customers(customers)
                .build();
    }

}
