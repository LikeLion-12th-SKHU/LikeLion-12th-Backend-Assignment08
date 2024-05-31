package org.likelion.likelionassignmentcrud.game.api;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.game.api.dto.request.GameSaveReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.request.GameUpdateReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.response.GameInfoResDto;
import org.likelion.likelionassignmentcrud.game.api.dto.response.GameListResDto;
import org.likelion.likelionassignmentcrud.game.application.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<GameInfoResDto> gameSave(@RequestBody @Valid GameSaveReqDto gameSaveReqDto) {
        GameInfoResDto gameInfoResDto = gameService.gameSave(gameSaveReqDto);

        return BaseResponse.success(SuccessCode.GAME_SAVE_SUCCESS, gameInfoResDto);
    }

    // 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameListResDto> gameFindAll() {
        GameListResDto gameListResDto = gameService.gameFindAll();

        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameListResDto);
    }

    // 게임 id로 조회
    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameInfoResDto> gameFindById(@PathVariable("gameId") Long gameId) {
        GameInfoResDto gameInfoResDto = gameService.gameFindById(gameId);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameInfoResDto);
    }

    // 개발사에 따른 게임 리스트 불러오기
    @GetMapping("/{developerName}/games")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameListResDto> gameFindAllByDeveloper(@PathVariable("developerName") String developerName) {
        GameListResDto gameListResDto = gameService.gameFindDeveloper(developerName);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameListResDto);
    }

    // 게임 수정
    @PatchMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameInfoResDto> gameUpdate(@PathVariable("gameId") Long gameId, @RequestBody @Valid GameUpdateReqDto gameUpdateReqDto) {
        GameInfoResDto gameInfoResDto = gameService.gameUpdate(gameId, gameUpdateReqDto);

        return BaseResponse.success(SuccessCode.GAME_UPDATE_SUCCESS, gameInfoResDto);
    }

    // 게임 삭제
    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameInfoResDto> gameDelete(@PathVariable("gameId") Long gameId) {
        GameInfoResDto gameInfoResDto = gameService.gameDelete(gameId);

        return BaseResponse.success(SuccessCode.GAME_DELETE_SUCCESS, gameInfoResDto);
    }
}
