package org.likelion.likelionassignmentcrud.student.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")

    private Long studentId;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private StudentGrade studentGrade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Major> major = new ArrayList<>();

    @Builder
    public Student(String name, int age, StudentGrade studentGrade) {
        this.name = name;
        this.age = age;
        this.studentGrade = studentGrade;
    }

    public void update(StudentUpdateReqDto studentUpdateReqDto) {
        this.name = studentUpdateReqDto.name();
        this.age = studentUpdateReqDto.age();
        this.studentGrade = studentUpdateReqDto.stuGrade();
    }
}
