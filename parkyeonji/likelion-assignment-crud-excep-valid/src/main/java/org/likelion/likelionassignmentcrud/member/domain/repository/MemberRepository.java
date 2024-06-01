package org.likelion.likelionassignmentcrud.member.domain.repository;

import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByClub(Club club);
}
