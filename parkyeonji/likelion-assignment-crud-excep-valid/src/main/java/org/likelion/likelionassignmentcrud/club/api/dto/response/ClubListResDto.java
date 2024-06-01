package org.likelion.likelionassignmentcrud.club.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ClubListResDto(
        List<ClubInfoResDto> clubs
) {
    public static ClubListResDto from(List<ClubInfoResDto> clubs) {
        return ClubListResDto.builder()
                .clubs(clubs)
                .build();
    }
}
