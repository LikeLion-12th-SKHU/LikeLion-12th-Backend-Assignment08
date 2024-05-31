package org.likelion.likelionassignmentcrud.developer.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.developer.domain.Developer;

import java.time.LocalDate;

@Builder
public record DeveloperInfoResDto(
        String name,
        String country,
        LocalDate establishedDate
) {
    public static DeveloperInfoResDto from(Developer developer) {
        return DeveloperInfoResDto.builder()
                .name(developer.getName())
                .country(developer.getCountry())
                .establishedDate(developer.getEstablishedDate())
                .build();
    }
}
