package org.likelion.likelioncrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200: 요청이 성공했다는 코드
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    ORDER_UPDATE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 수정되었습니다."),
    CONSUMER_UPDATE_SUCCESS(HttpStatus.OK, "고객이 성공적으로 수정되었습니다."),
    ORDER_DELETE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 삭제되었습니다."),
    CONSUMER_DELETE_SUCCESS(HttpStatus.OK, "고객이 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED: 요청이 성공했고, 자원의 생성이 일어났다는 코드
     */
    ORDER_SAVE_SUCCESS(HttpStatus.CREATED, "주문이 성공적으로 등록되었습니다."),
    CONSUMER_SAVE_SUCCESS(HttpStatus.CREATED, "고객이 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
