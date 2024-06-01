package org.likelion.likelionassignmentcrud.club.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.club.domain.Part; // 수정

@Builder
public record ClubInfoResDto(
        String name,
        String clubType,
        Part part
) {
    public static ClubInfoResDto from(Club club) {
        return ClubInfoResDto.builder()
                .name(club.getName())
                .clubType(club.getClubType())
                .part(club.getPart())
                .build();
    }
}
