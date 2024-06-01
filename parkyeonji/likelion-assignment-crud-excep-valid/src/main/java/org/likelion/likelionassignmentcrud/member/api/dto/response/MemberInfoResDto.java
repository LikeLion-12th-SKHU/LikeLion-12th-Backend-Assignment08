package org.likelion.likelionassignmentcrud.member.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.member.domain.Member;

@Builder
public record MemberInfoResDto(
        String name,
        int age,
        String email,
        String clubName
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .clubName(member.getClub().getName())
                .build();
    }
}
