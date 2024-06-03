package org.likelion.assignmentvalid.common.dto;

import lombok.*;
import org.likelion.assignmentvalid.common.error.ErrorCode;
import org.likelion.assignmentvalid.common.error.SuccessCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@Builder
public class BaseResponse<T> {
    public final int code; // 상태 코드를 나타내는 code
    private final String message; // 상태 메시지를 나타내는 message 필드
    private T data; // 제네릭 타입 T의 data 필드를 선언

    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }


    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }

}
