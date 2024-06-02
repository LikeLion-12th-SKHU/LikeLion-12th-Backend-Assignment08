package org.likelion.likelionassignmentcrud.common.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j // 로깅을 위한 Logger를 생성
@RestControllerAdvice // REST API 컨트롤러에 대한 예외 처리 어드바이스임을 나타내는 어노테이션
@Component // 클래스를 Spring 컴포넌트로 등록
@RequiredArgsConstructor
// 전역적인 예외 처리기능을 제공하는 클래스
public class CustomExceptionAdvice {
    /**
     * 500 Internal Server Error
     */
    // 예외 발생시 서버에러 500 발생
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleServerException(final Exception e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * custom error
     */
    @ExceptionHandler(CustomException.class)
    public BaseResponse handleCustomException(CustomException e) {
        log.error("CustomException: {}", e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }

    // 여기서부터 추가
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        // 에러 메시지를 생성
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // 응답 생성
        return BaseResponse.error(ErrorCode.VALIDATION_ERROR, convertMapToString(errorMap));
    }


    private String convertMapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1); // 마지막 띄어쓰기 제거
        sb.deleteCharAt(sb.length() - 1); // 마지막 쉼표 제거
        return sb.toString();
    }
}