package org.likelion.likelionassignmentcrud.common.exception;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;

public class NotFoundException extends CustomException{
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
