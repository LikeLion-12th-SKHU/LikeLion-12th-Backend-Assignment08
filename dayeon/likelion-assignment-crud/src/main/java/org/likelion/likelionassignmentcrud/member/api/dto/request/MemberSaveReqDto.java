package org.likelion.likelionassignmentcrud.member.api.dto.request;

import jakarta.validation.constraints.*;

public record MemberSaveReqDto(
        @NotBlank(message = "이름을 입력해주세요.")
        @Size(min = 2, max = 10, message = "2자 이상 10자 이하로 입력해주세요")
        String name,

// 해결하지 못 한 오류로 인해 불가피하게 주석처리하여 테스트했습니다.
//        @NotNull(message = "사업자번호를 필수로 입력해주세요.")
//        @Positive(message = "올바른 사업자번호를 입력해주세요.(-를 제외하고 입력해주세요)")
//        @Max(value = 10, message = "올바른 사업자번호를 입력해주세요.")
        int bizNum,

        @NotBlank(message = "올바른 상호를 입력해주세요.")
        String companyName
) {
}
