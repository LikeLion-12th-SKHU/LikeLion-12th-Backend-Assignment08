package org.likelion.likelioncrud.consumer.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrud.consumer.domain.Consumer;
import org.likelion.likelioncrud.consumer.domain.Part;

@Builder
public record ConsumerInfoResDto(
        String name,
        int age,
        String email,
        Part part
) {
    public static ConsumerInfoResDto from(Consumer consumer) {
        return ConsumerInfoResDto.builder()
                .name(consumer.getName())
                .age(consumer.getAge())
                .email(consumer.getEmail())
                .part(consumer.getPart())
                .build();
    }
}
