package org.likelion.likelionassignmentcrud.developer.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DeveloperListResDto(
        List<DeveloperInfoResDto> developers
) {
    public static DeveloperListResDto from(List<DeveloperInfoResDto> developers) {
        return DeveloperListResDto.builder()
                .developers(developers)
                .build();
    }
}
