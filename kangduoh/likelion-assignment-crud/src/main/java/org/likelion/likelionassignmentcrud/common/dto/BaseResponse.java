package org.likelion.likelionassignmentcrud.common.dto;

import lombok.*;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true) // null, 0 등 기본값으로 초기화
@Builder
public class BaseResponse<T> {

    private final int code; // 상태 코드
    private final String message; // 상태 메시지
    private T data; // 제네릭 타입 data 필드

    // 단순 상태코드와 메시지만 전달
    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    // 제네릭 메서드로 데이터를 포함하는 성공응답 객체 생성
    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    // ErrorCode 정보를 사용하여 오류 응답 BaseResponse 객체 생성
    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    // ErrorCode 정보를 사용하되, 사용자 정의 메시지로 오류 응답 BaseResponse 객체 생성
    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }
}
