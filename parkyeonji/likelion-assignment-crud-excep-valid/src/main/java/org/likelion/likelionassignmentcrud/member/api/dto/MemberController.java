package org.likelion.likelionassignmentcrud.member.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberListResDto;
import org.likelion.likelionassignmentcrud.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<MemberInfoResDto> memberSave(@RequestBody @Valid MemberSaveReqDto memberSaveReqDto) {
        MemberInfoResDto memberInfoResDto = memberService.memberSave(memberSaveReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_SAVE_SUCCESS, memberInfoResDto);
    }

    @GetMapping("/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberListResDto> MemberFindAll(@PathVariable("clubId") Long clubId) {
        MemberListResDto memberListResDto = memberService.memberFindClub(clubId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, memberListResDto);
    }

    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberInfoResDto> memberUpdate(@PathVariable("memberId") Long memberId,
                                               @RequestBody @Valid MemberUpdateReqDto memberUpdateReqDto) {
        MemberInfoResDto memberInfoResDto = memberService.memberUpdate(memberId, memberUpdateReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_UPDATE_SUCCESS, memberInfoResDto);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MemberInfoResDto> memberDelete(@PathVariable("memberId") Long memberId) {
        MemberInfoResDto memberInfoResDto = memberService.memberDelete(memberId);
        return BaseResponse.success(SuccessCode.MEMBER_DELETE_SUCCESS, memberInfoResDto);
    }
}
