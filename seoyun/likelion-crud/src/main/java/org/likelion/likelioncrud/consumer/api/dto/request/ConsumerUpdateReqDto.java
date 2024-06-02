package org.likelion.likelioncrud.consumer.api.dto.request;

import jakarta.validation.constraints.*;

public record ConsumerUpdateReqDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.") //NotBlank: null, "", " " 모두 허용 안 함
        @Size(min = 2, max=10, message = "2자 이상 10자 이하로 입력해주세요.") //Size: 크기를 제한할 때 사용
        String name,
        @NotBlank(message = "나이를 필수록 입력해야 합니다.")
        @Positive(message = "연나이를 입력해주세요.")
        @Max(value = 100, message = "1부터 100사이의 값만 입력할 수 있습니다.")
        int age,
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$",
                message = "이메일 형식에 맞지 않습니다.")
        String email
) {
}
