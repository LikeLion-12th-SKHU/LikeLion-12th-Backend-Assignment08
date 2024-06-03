package org.likelion.likelionassignmentcrud.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String name; //사업자명
    private int bizNum; //사업자번호
    private String companyName; //상호명

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, int bizNum, String companyName) {
        this.name = name;
        this.bizNum = bizNum;
        this.companyName = companyName;
    }

    public void update(MemberUpdateReqDto memberUpdateReqDto) {
        this.name = memberUpdateReqDto.name();
        this.bizNum = memberUpdateReqDto.bizNum();
        this.companyName = memberUpdateReqDto.companyName();
    }
}
