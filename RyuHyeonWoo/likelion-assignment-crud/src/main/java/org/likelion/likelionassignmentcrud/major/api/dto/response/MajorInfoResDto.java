package org.likelion.likelionassignmentcrud.major.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.major.domain.Major;

@Builder
public record MajorInfoResDto(
        String name,
        String part
) {
    public static MajorInfoResDto from (Major major) {
        return MajorInfoResDto.builder()
                .name(major.getName())
                .part(major.getPart())
                .build();
    }
}
