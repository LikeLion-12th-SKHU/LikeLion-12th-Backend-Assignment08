package org.likelion.jangsu1.Student.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu1.Rent.domain.Rent;
import org.likelion.jangsu1.Student.api.request.StudentUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스가 엔티티임을 알린다. JPA에 정의된 필드들을 바팅으로 하여 DB 베이스에 테이블을 만듦.
@Getter // get 메서드를 사용할 수 있게 한다.
// 아무 파라미터가 없는 기본 생성자의 생성(기본 생성자를 protected로 만듦. 보안과 엔티티의 프록시 조회 때문)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id     // PK(주키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정을 DB에 위임
    @Column(name = "student_id") // 매핑할 테이블의 컬럼 이름을 지정
    private Long studentId; // 학번, PK

    private String name;    // 이름
    private int age;        // 나이
    private String email;   // 이메일

    //Enum 타입을 엔티티 클래스의 속성으로 사용할 수 있다. (Enum 이름을 DB에 저장한다.)
    @Enumerated(EnumType.STRING)
    private Part part;      // 부서(FRONTEND, BACKEND, PM, QC)

    // 일대다(연관관계의 주인을 정하기 위해 / 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 영속 상태로 만듦, 모두 수행 / 고아객체를 삭제해 줌)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    @Builder
    private Student(String name, int age, String email, Part part) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.part = part;
    }

    // 학생 정보를 업데이트함
    public void updateStudent(StudentUpdateReqDto studentUpdateReqDto) {
        this.name = studentUpdateReqDto.name();
        this.age = studentUpdateReqDto.age();
        this.email = studentUpdateReqDto.email();
        this.part = studentUpdateReqDto.part();
    }
}
// 어노테이션 : 다른 프로그램에 유용한 정보를 제공하기 위해 사용하는 것, 주석과 같음

