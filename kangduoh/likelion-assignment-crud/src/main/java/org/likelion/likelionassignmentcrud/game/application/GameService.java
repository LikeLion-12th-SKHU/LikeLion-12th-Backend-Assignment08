package org.likelion.likelionassignmentcrud.game.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.developer.domain.Developer;
import org.likelion.likelionassignmentcrud.developer.domain.repository.DeveloperRepository;
import org.likelion.likelionassignmentcrud.game.api.dto.request.GameSaveReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.request.GameUpdateReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.response.GameInfoResDto;
import org.likelion.likelionassignmentcrud.game.api.dto.response.GameListResDto;
import org.likelion.likelionassignmentcrud.game.domain.Game;
import org.likelion.likelionassignmentcrud.game.domain.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;

    public GameService(GameRepository gameRepository, DeveloperRepository developerRepository) {
        this.gameRepository = gameRepository;
        this.developerRepository = developerRepository;
    }

    // Create
    @Transactional
    public GameInfoResDto gameSave(GameSaveReqDto gameSaveReqDto) {
        Developer developer = developerRepository.findById(gameSaveReqDto.developerId()).orElseThrow(()
                -> new NotFoundException(ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION,
                ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION.getMessage()
                        + gameSaveReqDto.developerId())
        );

        Game game = Game.builder()
                .name(gameSaveReqDto.name())
                .genre(gameSaveReqDto.genre())
                .platform(gameSaveReqDto.platform())
                .developer(developer)
                .build();

        gameRepository.save(game);
        return GameInfoResDto.from(game);
    }

    // Read All
    public GameListResDto gameFindAll() {
        List<Game> games = gameRepository.findAll();

        List<GameInfoResDto> gameInfoResDtoList = games.stream()
                .map(GameInfoResDto::from)
                .toList();

        return GameListResDto.from(gameInfoResDtoList);
    }

    // 개발사에 따른 게임 리스트 조회
    public GameListResDto gameFindDeveloper(String developerName) {
        Developer developer = developerRepository.findByName(developerName).orElseThrow(()
                -> new NotFoundException(ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION,
                ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION.getMessage()
                        + developerName)
        );

        List<Game> games = gameRepository.findByDeveloper(developer);
        List<GameInfoResDto> gameInfoResDtoList = games.stream()
                .map(GameInfoResDto::from)
                .toList();

        return GameListResDto.from(gameInfoResDtoList);
    }

    // Read One
    public GameInfoResDto gameFindById(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMES_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMES_NOT_FOUND_EXCEPTION.getMessage()
                                + gameId)
        );

        return GameInfoResDto.from(game);
    }

    // Update
    @Transactional
    public GameInfoResDto gameUpdate(Long gameId, GameUpdateReqDto gameUpdateReqDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMES_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMES_NOT_FOUND_EXCEPTION.getMessage()
                                + gameId)
        );

        game.update(gameUpdateReqDto);
        return GameInfoResDto.from(game);
    }

    // Delete
    @Transactional
    public GameInfoResDto gameDelete(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMES_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMES_NOT_FOUND_EXCEPTION.getMessage()
                                + gameId)
        );

        gameRepository.delete(game);
        return GameInfoResDto.from(game);
    }
}
