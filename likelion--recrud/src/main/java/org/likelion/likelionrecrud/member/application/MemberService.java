package org.likelion.likelionrecrud.member.application;

import lombok.RequiredArgsConstructor;

import org.likelion.likelionrecrud.exception.Error;
import org.likelion.likelionrecrud.exception.NotFoundException;
import org.likelion.likelionrecrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionrecrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionrecrud.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelionrecrud.member.domain.Member;
import org.likelion.likelionrecrud.member.domain.Part;
import org.likelion.likelionrecrud.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .part(Part.valueOf(memberSaveReqDto.part().toUpperCase()))
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow
                (() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()));
        member.update(memberUpdateReqDto);
    }

    public List<MemberInfoResDto> memberFindAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberInfoResDto::from)
                .collect(Collectors.toList());
    }

    public MemberInfoResDto memberFindOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow
                (() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION,Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()));
        return MemberInfoResDto.from(member);
    }

    @Transactional
    public void memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow
                (() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION,Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()));
        memberRepository.delete(member);
    }
}
