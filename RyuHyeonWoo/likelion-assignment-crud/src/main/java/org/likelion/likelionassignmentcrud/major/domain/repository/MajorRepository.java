package org.likelion.likelionassignmentcrud.major.domain.repository;

import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByStudent(Student student);
}
