package org.likelion.likelionassignmentcrud.customer.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.customer.domain.Customer;
import org.likelion.likelionassignmentcrud.customer.domain.Part;

@Builder
public record CustomerInfoResDto(

        String name,
        int age,
        Part part
) {

    public static CustomerInfoResDto from(Customer member) {
        return CustomerInfoResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .part(member.getPart())
                .build();
    }
}
