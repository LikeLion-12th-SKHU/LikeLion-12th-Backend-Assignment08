package org.likelion.jangsu1.common.exception;

import lombok.Getter;
import org.likelion.jangsu1.common.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException{
    // Error 변수 선언
    private final ErrorCode errorCode;

    // ErrorCode 객체와 예외 메시지를 받아 초기화
    // super(message) 호출을 통해 RuntimeException의 생성자를 호출하여 예외 메시지를 출력
    // this.errorCode = err0r을 통해 errorCode 필드에 전달받은 Error 객체를 할당
    public CustomException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }

    public int getHttpStatus(){
        return errorCode.getHttpStatusCode();
    }
}
