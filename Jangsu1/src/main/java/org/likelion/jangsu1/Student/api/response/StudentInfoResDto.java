package org.likelion.jangsu1.Student.api.response;

import lombok.Builder;
import org.likelion.jangsu1.Student.domain.Part;
import org.likelion.jangsu1.Student.domain.Student;

@Builder
public record StudentInfoResDto(Long studentId, int age, String name, String email, Part part) {
    public static StudentInfoResDto from(Student student) {
        return StudentInfoResDto.builder()
                .studentId(student.getStudentId())
                .name(student.getName())
                .age(student.getAge())
                .email(student.getEmail())
                .part(student.getPart())
                .build();
    }
}
