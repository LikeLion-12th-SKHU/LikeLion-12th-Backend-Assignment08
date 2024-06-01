package org.likelion.basic.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.basic.common.dto.BaseResponse;
import org.likelion.basic.common.error.ErrorCode;
import org.likelion.basic.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class CustomExceptionAdvice {

    // 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleServerException(final Exception e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 내부 커스텀 에러 처리
    @ExceptionHandler(CustomException.class)
    public BaseResponse handleCustomException(CustomException e) {
        log.error("CustomException: {}", e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return BaseResponse.error(ErrorCode.VALIDATION_ERROR, convertMapToString(errorMap));
    }

    private String convertMapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
