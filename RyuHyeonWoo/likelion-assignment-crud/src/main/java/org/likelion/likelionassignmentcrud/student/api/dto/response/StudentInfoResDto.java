package org.likelion.likelionassignmentcrud.student.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;

@Builder
public record StudentInfoResDto(
        String name,
        int age,
        Long studentId,
        StudentGrade stuGrade

) {
    public static StudentInfoResDto from(Student student) {
        return StudentInfoResDto.builder()
                .name(student.getName())
                .age(student.getAge())
                .studentId(student.getStudentId())
                .stuGrade(student.getStudentGrade())
                .build();
    }

}
