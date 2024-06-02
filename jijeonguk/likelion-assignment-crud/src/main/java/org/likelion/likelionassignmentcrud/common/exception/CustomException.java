package org.likelion.likelionassignmentcrud.common.exception;

import lombok.Getter;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;

@Getter
// 에외 처리의 최종 상속은 Throwable (물론 모든 객체의 최상위 객체는 Object임)
// unchecked 예외 이기 때문에 RuntimException을 상속 받고 있음
// 체크 예외는 Exception
// 근데 Throwable을 상속받지 않는 이유는?
// ->Throwable에는 Exception과 Error(시스템 예외)가 있는데 시스템 예외를 잡을 필요가 없음(복구가 불가능해서)
public class CustomException extends RuntimeException {

    // ErrorCode 의존 (ErrorCode 변수 선언)
    private final ErrorCode errorCode;


    public CustomException(ErrorCode errorCode, String message) {
        super(message); // 상속 받고 있는 예외 최상위 계층인 Throwable에 message 필드가 있음.
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }
}
