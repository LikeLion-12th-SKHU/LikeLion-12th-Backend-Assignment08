package org.likelion.assignmentvalid.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    PRODUCT_UPDATE_SUCCESS(HttpStatus.OK, "상품이 성공적으로 수정되었습니다."),
    ORDERS_UPDATE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 수정되었습니다."),
    PRODUCT_DELETE_SUCCESS(HttpStatus.OK, "상품이 성공적으로 삭제되었습니다."),
    ORDERS_DELETE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED
     */
    PRODUCT_SAVE_SUCCESS(HttpStatus.CREATED, "상품이 성공적으로 등록되었습니다."),
    ORDERS_SAVE_SUCCESS(HttpStatus.CREATED, "주문이 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
