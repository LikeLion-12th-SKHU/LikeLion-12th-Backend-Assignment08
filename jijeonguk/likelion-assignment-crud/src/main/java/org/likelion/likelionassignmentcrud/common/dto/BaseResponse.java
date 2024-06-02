package org.likelion.likelionassignmentcrud.common.dto;

import lombok.*;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@Builder
// BaseResponse는 API응답을 일관된 형식으로 제공하기 위한 클래스
// 제네릭 타입
public class BaseResponse<T> {

    private final int code; // 상태 코드를 나타냄
    private final String message; // 상태 메시지를 나타냄
    private T data; // 제네릭 타입 T의 data 필드를 선언


    // 전달할 data없이 단순히 상태코드와 메시지만 전달할 경우
    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }


    // 제네릭 메소드
    // 데이터를 포함하는 성공 응답 BaseResponse 객체를 생성
    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    // ErrorCode 정보를 사용하여 오류 응답 BaseResponse 객체를 생성
    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    // ErrorCode 정보를 사용하되, 사용자 정의 메시지로 오류 응답 BaseResponse 객체를 생성
    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }

}