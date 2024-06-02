package org.likelion.jangsu1.Rent.appication;

import org.likelion.jangsu1.Rent.api.request.RentSaveReqDto;
import org.likelion.jangsu1.Rent.api.request.RentUpdateReqDto;
import org.likelion.jangsu1.Rent.api.response.RentInfoResDto;
import org.likelion.jangsu1.Rent.api.response.RentListResDto;
import org.likelion.jangsu1.Rent.domain.Rent;
import org.likelion.jangsu1.Rent.domain.Repository.RentRepository;
import org.likelion.jangsu1.Student.domain.Repository.StudentRepository;
import org.likelion.jangsu1.Student.domain.Student;
import org.likelion.jangsu1.common.error.ErrorCode;
import org.likelion.jangsu1.common.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 이 어노테이션은 주로 서비스 인터페이스를 구현하는 클래스에서 사용
@Service
// 실행 중 예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백함.(읽기 전용)
@Transactional(readOnly = true)
public class RentService {
    private final StudentRepository studentRepository;
    private final RentRepository rentRepository;

    public RentService(StudentRepository studentRepository, RentRepository rentRepository) {
        this.studentRepository = studentRepository;
        this.rentRepository = rentRepository;
    }

    // 대출 명단의 생성
    @Transactional
    public RentInfoResDto rentSave(RentSaveReqDto rentSaveReqDto) {
        Student student = studentRepository.findById(rentSaveReqDto.studentId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENTS_NOT_FOUND_EXCEPTION.getMessage()
                                + rentSaveReqDto.studentId())
                );

        Rent rent = Rent.builder()
                .student(student)
                .rentTime(rentSaveReqDto.rentTime())
                .returnTime(rentSaveReqDto.returnTime())
                .bookName(rentSaveReqDto.bookName())
                .build();

        rentRepository.save(rent);
        return RentInfoResDto.from(rent);
    }

    // 전부 불러오기
    public RentListResDto rentFindAll(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.RENTS_NOT_FOUND_EXCEPTION.getMessage())
                );

        List<Rent> rents = rentRepository.findByStudent(student);
        List<RentInfoResDto> rentInfoResDtoList = rents.stream()
                .map(RentInfoResDto::from).toList();

        return RentListResDto.from(rentInfoResDtoList);
    }

    // 업데이트
    @Transactional
    public RentInfoResDto rentUpdate(Long studentId, RentUpdateReqDto rentUpdateReqDto) {
        Rent rent = rentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.RENTS_NOT_FOUND_EXCEPTION.getMessage() + studentId)
                );

        rent.update(rentUpdateReqDto);
        return RentInfoResDto.from(rent);
    }

    // 삭제
    @Transactional
    public RentInfoResDto rentDelete(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.RENTS_NOT_FOUND_EXCEPTION.getMessage() + rentId)
                );

        rentRepository.delete(rent);
        return RentInfoResDto.from(rent);
    }

    // 전부 삭제
    @Transactional
    public RentInfoResDto rentDeleteAll(Long studentId) {
        Rent rent = rentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RENTS_NOT_FOUND_EXCEPTION,
                        ErrorCode.RENTS_NOT_FOUND_EXCEPTION.getMessage() + studentId)
                );

        rentRepository.deleteAll();
        return RentInfoResDto.from(rent);
    }
}
