package org.likelion.jangsu1.Student.application;

import org.likelion.jangsu1.Student.api.request.StudentSaveReqDto;
import org.likelion.jangsu1.Student.api.request.StudentUpdateReqDto;
import org.likelion.jangsu1.Student.api.response.StudentInfoResDto;
import org.likelion.jangsu1.Student.api.response.StudentListResDto;
import org.likelion.jangsu1.Student.domain.Repository.StudentRepository;
import org.likelion.jangsu1.Student.domain.Student;
import org.likelion.jangsu1.common.error.ErrorCode;
import org.likelion.jangsu1.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 이 어노테이션은 주로 서비스 인터페이스를 구현하는 클래스에서 사용
@Service
// 실행 중 예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백함.(읽기 전용)
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 생성
    @Transactional
    public StudentInfoResDto studentSave(StudentSaveReqDto studentSaveReqDto) {
        Student student = Student.builder()
                .name(studentSaveReqDto.name())
                .age(studentSaveReqDto.age())
                .email(studentSaveReqDto.email())
                .part(studentSaveReqDto.part()).build();

        studentRepository.save(student);
        return StudentInfoResDto.from(student);
    }

    // 한명 불러오기
    public StudentInfoResDto studentFindOne(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION.getMessage() + studentId));

        return StudentInfoResDto.from(student);
    }

    // 전부 불러오기
    public StudentListResDto studentFindAll(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        List<StudentInfoResDto> studentInfoResDtoList = students.stream()
                .map(StudentInfoResDto::from).toList();

        return StudentListResDto.from(studentInfoResDtoList);
    }

    // 업데이트
    @Transactional
    public StudentInfoResDto studentUpdate(Long studentId, StudentUpdateReqDto studentUpdateReqDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION.getMessage() + studentId));

        student.updateStudent(studentUpdateReqDto);
        return StudentInfoResDto.from(student);
    }

    // 삭제
    @Transactional
    public StudentInfoResDto studentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION.getMessage() + studentId));

        studentRepository.delete(student);
        return StudentInfoResDto.from(student);
    }
}
