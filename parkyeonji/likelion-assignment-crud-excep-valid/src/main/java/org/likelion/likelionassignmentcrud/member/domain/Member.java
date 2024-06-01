package org.likelion.likelionassignmentcrud.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String name;
    private int age;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Builder
    private Member(String name, int age, String email, Club club) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.club = club;
    }

    // 추가
    public void update(MemberUpdateReqDto memberUpdateReqDto) {
        this.name = memberUpdateReqDto.name();
        this.age = memberUpdateReqDto.age();
        this.email = memberUpdateReqDto.email();
    }
}
