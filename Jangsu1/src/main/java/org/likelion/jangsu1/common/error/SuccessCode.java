package org.likelion.jangsu1.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK (Httpstatus code, 요청이 성공했음)
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    STUDENT_UPDATE_SUCCESS(HttpStatus.OK, "학생 정보가 성공적으로 수정되었습니다."),
    RENT_UPDATE_SUCCESS(HttpStatus.OK, "대출 목록이 성공적으로 수정되었습니다."),
    STUDENT_DELETE_SUCCESS(HttpStatus.OK, "학생 정보가 성공적으로 삭제되었습니다."),
    RENT_DELETE_SUCCESS(HttpStatus.OK, "대출 목록이 성공적으로 삭제되었습니다."),

    // 201 CREATED (Httpstatus code,요청이 성공했고 자원의 생성이 일어남)
    STUDENT_SAVE_SUCCESS(HttpStatus.CREATED, "사용자가 성공적으로 등록되었습니다."),
    RENT_SAVE_SUCCESS(HttpStatus.CREATED, "대출 정보가 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
