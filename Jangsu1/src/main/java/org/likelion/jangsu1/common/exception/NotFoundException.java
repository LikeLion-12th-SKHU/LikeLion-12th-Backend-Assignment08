package org.likelion.jangsu1.common.exception;

import org.likelion.jangsu1.common.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
