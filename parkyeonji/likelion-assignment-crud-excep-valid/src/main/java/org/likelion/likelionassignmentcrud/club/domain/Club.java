package org.likelion.likelionassignmentcrud.club.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long clubId;

    private String name;
    private String clubType;

    @Enumerated(EnumType.STRING) // 수정
    private Part part;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @Builder
    private Club(String name, String clubType, Part part) { // 수정
        this.name = name;
        this.clubType = clubType;
        this.part = part; // 수정
    }

    //추가
    public void update(ClubUpdateReqDto clubUpdateReqDto) {
        this.name = clubUpdateReqDto.name();
        this.clubType = clubUpdateReqDto.clubType();
    }
}


