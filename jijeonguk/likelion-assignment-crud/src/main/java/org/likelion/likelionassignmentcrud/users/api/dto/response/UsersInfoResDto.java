package org.likelion.likelionassignmentcrud.users.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.users.domain.PayOption;
import org.likelion.likelionassignmentcrud.users.domain.Users;
@Builder
public record UsersInfoResDto(
        String name,
        String phoneNumber,
        String email,
        PayOption option
) {
    public static UsersInfoResDto from(Users users){
        return UsersInfoResDto.builder()
                .name(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .option(users.getOption())
                .build();
    }
}
