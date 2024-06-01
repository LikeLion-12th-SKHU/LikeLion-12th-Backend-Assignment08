package org.likelion.likelionassignmentcrud.member.application;

import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.club.domain.repository.ClubRepository;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberListResDto;
import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public MemberService(ClubRepository clubRepository, MemberRepository memberRepository) {
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
    }

    // Create
    @Transactional
    public MemberInfoResDto memberSave(MemberSaveReqDto memberSaveReqDto) {
        Club club = clubRepository.findById(memberSaveReqDto.clubId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.INTERNAL_SERVER_ERROR.getMessage() + memberSaveReqDto.clubId())
        );

        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .email(memberSaveReqDto.email())
                .club(club)
                .build();

        memberRepository.save(member);
        return MemberInfoResDto.from(member);
    }

    // Read
    public MemberListResDto memberFindClub(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CLUBS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CLUBS_NOT_FOUND_EXCEPTION.getMessage() + clubId)
        );

        List<Member> members = memberRepository.findByClub(club);
        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtoList);
    }


    // update
    @Transactional
    public MemberInfoResDto memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        member.update(memberUpdateReqDto);
        return MemberInfoResDto.from(member);
    }
    // delete
    @Transactional
    public MemberInfoResDto memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        memberRepository.delete(member);
        return MemberInfoResDto.from(member);
    }
}
