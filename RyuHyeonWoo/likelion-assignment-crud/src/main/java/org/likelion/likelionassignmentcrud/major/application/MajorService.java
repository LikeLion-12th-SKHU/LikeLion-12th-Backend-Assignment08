package org.likelion.likelionassignmentcrud.major.application;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorSaveReqDto;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.major.api.dto.response.MajorInfoResDto;
import org.likelion.likelionassignmentcrud.major.api.dto.response.MajorListResDto;
import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.major.domain.repository.MajorRepository;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.application.StudentService;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.likelion.likelionassignmentcrud.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MajorService {
    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;

    public MajorService(StudentRepository studentRepository, MajorRepository majorRepository) {
        this.studentRepository = studentRepository;
        this.majorRepository = majorRepository;
    }

    @Transactional
    public void majorSave(MajorSaveReqDto majorSaveReqDto) {
        Student student = studentRepository.findById(majorSaveReqDto.studentId())
                .orElseThrow(IllegalArgumentException::new);

        Major major = Major.builder()
                .name(majorSaveReqDto.name())
                .part(majorSaveReqDto.part())
                .student(student)
                .build();

        majorRepository.save(major);
    }

    public MajorListResDto majorFindStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(IllegalArgumentException::new);

        List<Major> majors = majorRepository.findByStudent(student);
        List<MajorInfoResDto> majorInfoResDtoList = majors.stream()
                .map(MajorInfoResDto::from)
                .toList();

        return MajorListResDto.from(majorInfoResDtoList);
    }

    @Transactional
    public void majorUpdate(Long majorId, MajorUpdateReqDto majorUpdateReqDto) {
        Major major = majorRepository.findById(majorId).orElseThrow(IllegalArgumentException::new);

        major.update(majorUpdateReqDto);
    }

    @Transactional
    public void majorDelete(Long majorId) {
        Major major = majorRepository.findById(majorId)
                .orElseThrow(IllegalArgumentException::new);

        majorRepository.delete(major);
    }
}
