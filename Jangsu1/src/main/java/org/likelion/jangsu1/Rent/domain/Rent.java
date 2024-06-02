package org.likelion.jangsu1.Rent.domain;

import jakarta.persistence.*;
import lombok.*;
import org.likelion.jangsu1.Rent.api.request.RentUpdateReqDto;
import org.likelion.jangsu1.Student.domain.Student;

@Entity // 이 클래스가 엔티티임을 알림, JPA에서 정의된 필드들을 바탕으로 데이터 베이스에 테이블을 만들어 준다.
@Getter // get 메서드를 사용할 수 있게 한다.
// 아무 파라미터가 없는 기본 생성자의 생성(기본 생성자를 protected로 만듦. 보안과 엔티티의 프록시 조회 때문)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rent {

    @Id // PK(주키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 설정을 DB에 위임
    @Column(name = "rent_id")   // 매핑할 테이블의 컬럼 이름의 지정
    private Long rentId;        // 대여 도서의 고유넘버, PK

    private String rentTime;    // 빌린 시간
    private String returnTime;  // 반납할 시간
    private String bookName;    // 책 이름

    // 다대일, 한 사람이 여러 권의 책을 빌릴 수 있으므로, (연관된 엔티티를 프록시로 조회, 사용 시 프록시를 초기화하면서 DB를 조회)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id") // 외래키의 매핑, 매핑할 외래키의 이름을 입력한다.
    private Student student;

    // 엔티티 객체 생성 시 빌더 패턴을 사용하여 만들 수 있도록 지정하는 어노테이션
    @Builder
    private Rent(String rentTime, String returnTime, String bookName, Student student) {
        // 의존관계 설정
        this.rentTime = rentTime;
        this.returnTime = returnTime;
        this.bookName = bookName;
        this.student = student;
    }

    public void update(RentUpdateReqDto rentUpdateReqDto) {
        this.rentTime = rentUpdateReqDto.rentTime();
        this.returnTime = rentUpdateReqDto.returnTime();
        this.bookName = rentUpdateReqDto.bookName();
    }
}
// 어노테이션 : 다른 프로그램에 유용한 정보를 제공하기 위해 사용하는 것, 주석과 같음