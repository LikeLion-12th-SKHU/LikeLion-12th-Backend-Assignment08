package org.likelion.likelionassignmentcrud.student.application;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentListResDto;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;
import org.likelion.likelionassignmentcrud.student.domain.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Transactional
    public StudentInfoResDto studentSave(StudentSaveReqDto studentSaveReqDto) {
        Student student = Student.builder()
                .name(studentSaveReqDto.name())
                .age(studentSaveReqDto.age())
                .studentGrade(studentSaveReqDto.stuGrade())
                .build();

        studentRepository.save(student);

        return StudentInfoResDto.from(student);
    }

    public StudentListResDto studentFindAll(Pageable pageable) {
        Page<Student> student = studentRepository.findAll(pageable);

        List<StudentInfoResDto> studentInfoResDtoList = student.stream()
                .map(StudentInfoResDto::from)
                .collect(Collectors.toList());

        return StudentListResDto.from(studentInfoResDtoList);
    }

    public StudentInfoResDto studentFindOne(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.STUDENT_NOT_FOUND_EXCEPTION, ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()
                + studentId));

        return StudentInfoResDto.from(student);
    }

    @Transactional
    public StudentInfoResDto studentUpdate(Long studentId, StudentUpdateReqDto studentUpdateReqDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.STUDENT_NOT_FOUND_EXCEPTION, ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()
                +studentId));

        student.update(studentUpdateReqDto);

        return StudentInfoResDto.from(student);
    }

    @Transactional
    public StudentInfoResDto StudentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.STUDENT_NOT_FOUND_EXCEPTION, ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()
                +studentId));

        studentRepository.delete(student);

        return StudentInfoResDto.from(student);
    }
}