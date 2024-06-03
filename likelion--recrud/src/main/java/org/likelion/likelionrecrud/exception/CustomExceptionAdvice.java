package org.likelion.likelionrecrud.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionAdvice {

	/**
	 * 500 Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public BaseResponse<?> handlerServerException(final Exception e){
		log.error("Internal Server Error: {}", e.getMessage(), e);
		return BaseResponse.error(Error.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom Error 400
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse<?> handleValidationException(MethodArgumentNotValidException e){
		// 에러 메시지 생성
		return BaseResponse.error(Error.VALIDATION_ERROR, convertMapToString(createBindErrorMessage(e)));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public BaseResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
		log.error("Custom Exception: {}", e.getMessage(), e);
		return BaseResponse.error(Error.PARAMETER_NOT_VALID_ERROR, Error.PARAMETER_NOT_VALID_ERROR.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	public BaseResponse<?> handleCustomJakartaValidationException(ValidationException e){ //내가 throw한 애들이 뜨도록
		return BaseResponse.error(Error.VALIDATION_ERROR, String.valueOf(e.getCause()).split(": ")[1]);
	}

	@ExceptionHandler(CustomException.class)
	public BaseResponse<?> handlerCustomException(CustomException e){
		log.error("Custom Exception: {}", e.getMessage(), e);
		return BaseResponse.error(e.getError(), e.getMessage());
	}

	private Map<String,String> createBindErrorMessage(BindException e){
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldError: e.getBindingResult().getFieldErrors()){
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errorMap;
	}

	private String convertMapToString(Map<String, String> map){
		StringJoiner sj = new StringJoiner(", ");
		for(Map.Entry<String, String> entry : map.entrySet()){
			sj.add(entry.getKey()+" : "+entry.getValue());
		}
		return sj.toString();
	}

}
