package org.likelion.jangsu1.Rent.api.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RentListResDto(List<RentInfoResDto> rents) {
    public static RentListResDto from(List<RentInfoResDto> rents) {
        return RentListResDto.builder().rents(rents).build();
    }
}
