package org.likelion.likelionassignmentcrud.consumer.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.consumer.domain.Consumer;
import org.likelion.likelionassignmentcrud.consumer.domain.Part;

@Builder
public record ConsumerInfoResDto(
        String name,
        int age,
        Part part
) {
    public static ConsumerInfoResDto from(Consumer consumer) {
        return ConsumerInfoResDto.builder()
                .name(consumer.getName())
                .age(consumer.getAge())
                .part(consumer.getPart())
                .build();
    }
}
