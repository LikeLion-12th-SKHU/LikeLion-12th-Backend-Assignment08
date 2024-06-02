package org.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    FOOD_UPDATE_SUCCESS(HttpStatus.OK, "음식이 성공적으로 수정되었습니다."),
    ORDERS_UPDATE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 수정되었습니다."),
    FOOD_DELETE_SUCCESS(HttpStatus.OK, "음식이 성공적으로 삭제되었습니다."),
    ORDERS_DELETE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 삭제되었습니다."),

    FOOD_SAVE_SUCCESS(HttpStatus.CREATED, "음식이 성공적으로 등록되었습니다."),
    ORDERS_SAVE_SUCCESS(HttpStatus.CREATED, "주문이 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
