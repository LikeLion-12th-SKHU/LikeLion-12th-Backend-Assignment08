package org.likelion.likelionassignmentcrud.game.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GameListResDto(
        List<GameInfoResDto> games
) {
    public static GameListResDto from(List<GameInfoResDto> games) {
        return GameListResDto.builder()
                .games(games)
                .build();
    }
}
