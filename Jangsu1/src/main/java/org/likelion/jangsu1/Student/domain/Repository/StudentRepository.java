package org.likelion.jangsu1.Student.domain.Repository;

import org.likelion.jangsu1.Student.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속한다. 제네릭스 타입으로 <엔티티의 타입, 엔티티의 PK의 속성타입>을 지정한다.
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
}
