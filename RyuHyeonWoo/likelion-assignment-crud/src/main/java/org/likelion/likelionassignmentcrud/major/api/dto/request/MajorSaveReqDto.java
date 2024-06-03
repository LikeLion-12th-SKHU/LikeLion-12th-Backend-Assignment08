package org.likelion.likelionassignmentcrud.major.api.dto.request;

public record MajorSaveReqDto(
        Long studentId,
        String name,
        String part
) {
}
