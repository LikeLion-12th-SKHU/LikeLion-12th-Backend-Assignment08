package org.likelion.likelionassignmentcrud.major.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MajorListResDto(
        List<MajorInfoResDto> majors
) {
    public static MajorListResDto from(List<MajorInfoResDto> majors) {
        return MajorListResDto.builder()
                .majors(majors)
                .build();
    }
}
