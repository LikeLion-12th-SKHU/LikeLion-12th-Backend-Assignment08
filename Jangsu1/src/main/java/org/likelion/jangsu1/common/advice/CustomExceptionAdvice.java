package org.likelion.jangsu1.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.jangsu1.common.dto.BaseResponse;
import org.likelion.jangsu1.common.error.ErrorCode;
import org.likelion.jangsu1.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionAdvice {
    // 500 INTERNAL_SERVER_ERROR
    // 원인 모를 이유의 예외 발생 시
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleSeverException(final Exception e) {
        log.error("Internal Server Error : {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // Custom Error
    @ExceptionHandler(CustomException.class)
    public BaseResponse handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getErrorCode(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }

    // 예외 발생 시 클라이언트에게 400을 반환함
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // 클래스 타입의 예외를 처리하는 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        // 에러 메시지의 생성
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        // 응답의 생성
        return BaseResponse.error(ErrorCode.VALIDATION_ERROR, convertMapToString(errorMap));
    }

    // Map을 문자열로 변환
    private String convertMapToString(Map<String, String> map) {
        StringBuilder stringBuilder =new StringBuilder();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        return stringBuilder.toString();
    }
}
