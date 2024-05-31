package org.likelion.likelionassignmentcrud.common.error;

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
    GAME_UPDATE_SUCCESS(HttpStatus.OK, "게임이 성공적으로 수정되었습니다."),
    DEVELOPER_UPDATE_SUCCESS(HttpStatus.OK, "개발사가 성공적으로 수정되었습니다."),
    GAME_DELETE_SUCCESS(HttpStatus.OK, "게임이 성공적으로 삭제되었습니다."),
    DEVELOPER_DELETE_SUCCESS(HttpStatus.OK, "개발사가 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED
     */
    GAME_SAVE_SUCCESS(HttpStatus.CREATED, "게임이 성공적으로 등록되었습니다."),
    DEVELOPER_SAVE_SUCCESS(HttpStatus.CREATED, "개발사가 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
