package org.likelion.likelionassignmentcrud.common.exception;

import lombok.Getter;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }

}