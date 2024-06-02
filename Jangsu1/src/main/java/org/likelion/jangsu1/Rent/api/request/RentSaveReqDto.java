package org.likelion.jangsu1.Rent.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RentSaveReqDto(
        @NotNull(message = "학번을 입력하십시오")
        @Positive(message = "학번은 0보다 커야 합니다!")
        Long studentId,

        @NotNull(message = "대츨 시간을 입력하시오.")
        @Size(min = 8, max = 8, message = "입력 형식 : 8자리, 년도와 월, 일을 입력하시오.")
        String rentTime,

        @NotNull(message = "반납 시간을 입력하시오.")
        @Size(min = 8, max = 8, message = "입력 형식 : 8자리, 년도와 월, 일을 입력하시오.")
        String returnTime,

        @NotNull(message = "책 이름을 입력하시오.")
        @Size(min = 1, max = 50, message = "최대 50자까지 입력할 수 있습니다.")
        String bookName
) {
}

