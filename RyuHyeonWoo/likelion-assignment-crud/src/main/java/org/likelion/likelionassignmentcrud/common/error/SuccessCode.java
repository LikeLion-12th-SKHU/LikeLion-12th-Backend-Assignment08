package org.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    MAJOR_UPDATE_SUCCESS(HttpStatus.OK, "전공이 성공적으로 수정됐습니다."),
    STUDENT_UPDATE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 수정됐습니다."),
    MAJOR_DELETE_SUCCESS(HttpStatus.OK, "전공이 성공적으로 삭제됐습니다."),
    STUDENT_DELETE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 삭제됐습니다."),

    /**
     * 201 CREATED
     */

    MAJOR_SAVE_SUCCESS(HttpStatus.CREATED, "전공이 성공적으로 등록되었습니다,"),
    STUDENT_SAVE_SUCCESS(HttpStatus.CREATED, "학생이 성공적으로 등록되었습니다,");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}