package org.likelion.likelionassignmentcrud.consumer.api.dto.request;

import org.likelion.likelionassignmentcrud.consumer.domain.Part;

public record ConsumerSaveReqDto(
        String name,
        int age,
        Part part
) {
}
