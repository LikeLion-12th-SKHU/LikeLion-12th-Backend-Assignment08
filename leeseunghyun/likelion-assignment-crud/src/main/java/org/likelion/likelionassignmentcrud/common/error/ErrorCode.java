package org.likelion.likelionassignmentcrud.common.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    FOODS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 음식이 없습니다. foodId = ", "NOT_FOUND_404"),
    ORDERSS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 주문이 없습니다. ordersId = ", "NOT_FOUND_404"),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다.", "BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

