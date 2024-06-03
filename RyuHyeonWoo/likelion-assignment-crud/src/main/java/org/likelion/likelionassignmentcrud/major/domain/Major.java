package org.likelion.likelionassignmentcrud.major.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.domain.Student;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Long majorId;

    private String name;
    private String part;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    private Major(String name, String part, Student student) {
        this.name = name;
        this.part = part;
        this.student = student;
    }

    public void update(MajorUpdateReqDto majorUpdateReqDto) {
        this.part = majorUpdateReqDto.part();
    }

}
