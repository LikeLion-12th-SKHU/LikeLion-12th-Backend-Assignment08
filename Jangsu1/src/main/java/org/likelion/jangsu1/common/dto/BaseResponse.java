package org.likelion.jangsu1.common.dto;

import lombok.*;
import org.likelion.jangsu1.common.error.ErrorCode;
import org.likelion.jangsu1.common.error.SuccessCode;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
// API 응답을 일관된 형식으로 제공하기 위한 클래스
// 상태코드, 메시지, 데이터 등을 공통왼 응답구조를 제공하기 위함, 이를 통해 발생하는 예외를 일관적으로 반환할 수 있다.
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    //전달할 data 없이 상대코드와 메시지만 전달할 경우
    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    // 오버로딩
    // 데이터를 포함하는 성공 응답, BaseResponse 객체를 생성
    // 제네릭 메소드 (제네릭이란? 사용자가 클래스의 인스턴스를 생성할 때 / 제네릭 메서드틑 호출할 때 자료의 타입을 정하는 것을 의미함)
    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    // ErrorCode 정보를 사용하여 오류응답 BaseResponse 객체를 생성
    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    //
    // ErrorCode 정보를 사용하되, 사용자 정의 메시지로 오류 응답 BaseResponse 객체를 생성
    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse(error.getHttpStatusCode(), message);
    }
}
