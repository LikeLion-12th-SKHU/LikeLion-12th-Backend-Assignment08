package org.likelion.likelionassignmentcrud.consumer.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ConsumerListResDto(
        List<ConsumerInfoResDto> consumers
) {
    public static ConsumerListResDto from(List<ConsumerInfoResDto> consumers) {
        return ConsumerListResDto.builder()
                .consumers(consumers)
                .build();
    }
}
