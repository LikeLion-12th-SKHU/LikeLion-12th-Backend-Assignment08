package org.likelion.likelionassignmentcrud.member.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberListResDto;
import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.member.domain.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    // Create
    @Transactional
    public MemberInfoResDto memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .bizNum(memberSaveReqDto.bizNum())
                .companyName(memberSaveReqDto.companyName())
                .build();

        memberRepository.save(member);
        return MemberInfoResDto.from(member);
    }

    // Read All
    public MemberListResDto memberFindAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // Read one
    public MemberInfoResDto memberFindOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        return MemberInfoResDto.from(member);
    }

    // Update
    @Transactional
    public MemberInfoResDto memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage() + memberId)
        );

        member.update(memberUpdateReqDto);
        return MemberInfoResDto.from(member);
    }

    //Delete
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
