package org.likelion.likelionassignmentcrud.game.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.game.domain.Game;
import org.likelion.likelionassignmentcrud.game.domain.Platform;

@Builder
public record GameInfoResDto(
        String name,
        String genre,
        Platform platform,
        String developerName
) {
    public static GameInfoResDto from(Game game) {
        return GameInfoResDto.builder()
                .name(game.getName())
                .genre(game.getGenre())
                .platform(game.getPlatform())
                .developerName(game.getDeveloper().getName())
                .build();
    }
}
