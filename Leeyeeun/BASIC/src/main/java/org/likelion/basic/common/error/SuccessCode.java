package org.likelion.basic.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.logging.Handler;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    SHOP_UPDATE_SUCCESS(HttpStatus.OK, "성공적으러 수정되었습니다."),
    CUSTOMER_UPDATE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 수정되었습니다."),
    SHOP_DELETE_SUCCESS(HttpStatus.OK, "성공적으로 삭제되었습니다."),
    CUSTOMER_DELETE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 삭제되었습니다."),

    // 201 CREATED
    SHOP_SAVE_SUCCESS(HttpStatus.CREATED, "매장 정보가 성공적으로 등록되었습니다."),
    MEMBER_SAVE_SUCCESS(HttpStatus.CREATED, "사용자가 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
