package org.likelion.likelionassignmentcrud.customer.api.dto.request;

import org.likelion.likelionassignmentcrud.customer.domain.Part;

public record CustomerSaveReqDto(

        String name,
        int age,
        Part part
) {
}
