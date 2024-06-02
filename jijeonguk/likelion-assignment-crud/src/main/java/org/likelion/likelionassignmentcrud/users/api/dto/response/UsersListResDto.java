package org.likelion.likelionassignmentcrud.users.api.dto.response;

import lombok.Builder;

import java.util.List;
@Builder
public record UsersListResDto(

        List<UsersInfoResDto> users
) {
    public static UsersListResDto from(List<UsersInfoResDto>users){
        return UsersListResDto.builder()
                .users(users)
                .build();
    }
}
