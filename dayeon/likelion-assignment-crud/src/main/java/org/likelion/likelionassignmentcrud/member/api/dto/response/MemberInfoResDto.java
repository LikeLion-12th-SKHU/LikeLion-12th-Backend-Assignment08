package org.likelion.likelionassignmentcrud.member.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.member.domain.Member;

@Builder
public record MemberInfoResDto(
        String name,
        int bizNum,
        String companyName
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .bizNum(member.getBizNum())
                .companyName(member.getCompanyName())
                .build();
    }
}
