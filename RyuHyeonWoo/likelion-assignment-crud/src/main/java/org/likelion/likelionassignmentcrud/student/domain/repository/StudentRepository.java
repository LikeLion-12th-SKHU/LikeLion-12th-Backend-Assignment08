package org.likelion.likelionassignmentcrud.student.domain.repository;

import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
}
