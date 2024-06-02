package org.likelion.jangsu1.Student.api.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StudentListResDto(List<StudentInfoResDto> students) {
    public static StudentListResDto from(List<StudentInfoResDto> students){
        return StudentListResDto.builder().students(students).build();
    }
}
