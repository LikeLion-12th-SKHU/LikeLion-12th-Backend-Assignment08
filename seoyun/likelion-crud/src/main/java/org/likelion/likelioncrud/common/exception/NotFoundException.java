package org.likelion.likelioncrud.common.exception;

import org.likelion.likelioncrud.common.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
