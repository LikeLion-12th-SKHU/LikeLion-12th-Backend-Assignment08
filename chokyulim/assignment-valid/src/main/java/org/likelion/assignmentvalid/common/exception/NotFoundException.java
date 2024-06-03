package org.likelion.assignmentvalid.common.exception;

import org.likelion.assignmentvalid.common.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
