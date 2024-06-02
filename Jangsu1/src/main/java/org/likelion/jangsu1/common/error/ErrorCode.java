package org.likelion.jangsu1.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    // 400 BAD_REQUEST
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다.", "BAD_REQUEST_400"),

    // 404 NOT_FOUND
    RENTS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당하는 명단이 없습니다. RentID : ", "NOT_FOUND_404"),
    STUDENTS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 학생을 찾을 수 없습니다. studentId : ", "NOT_FOUND_404"),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생하였습니다.", "INTERNAL_SERVER_ERROR_500");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
