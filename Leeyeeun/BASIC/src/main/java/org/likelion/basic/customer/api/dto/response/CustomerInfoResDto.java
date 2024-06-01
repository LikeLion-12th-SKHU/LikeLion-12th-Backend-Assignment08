package org.likelion.basic.customer.api.dto.response;

import lombok.Builder;
import org.likelion.basic.customer.domain.Customer;
import org.likelion.basic.customer.domain.Part;

@Builder
public record CustomerInfoResDto(

        String name,
        int age,
        String email,
        Part part
) {

    public static CustomerInfoResDto from(Customer customer) {
        return CustomerInfoResDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .part(customer.getPart())
                .build();
    }
}
