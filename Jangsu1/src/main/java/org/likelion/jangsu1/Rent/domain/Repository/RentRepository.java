package org.likelion.jangsu1.Rent.domain.Repository;

import org.likelion.jangsu1.Rent.domain.Rent;
import org.likelion.jangsu1.Student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository를 상속한다. 제네릭스 타입으로 <엔티티의 타입, 엔티티의 PK의 속성타입>을 지정한다.
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByStudent(Student student);
}
