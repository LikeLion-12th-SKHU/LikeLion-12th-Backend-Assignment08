package org.likelion.likelionassignmentcrud.student.api;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentListResDto;
import org.likelion.likelionassignmentcrud.student.application.StudentService;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<StudentInfoResDto> studentSave(@RequestBody @Valid StudentSaveReqDto studentSaveReqDto) {
        StudentInfoResDto studentInfoResDto = studentService.studentSave(studentSaveReqDto);
        return BaseResponse.success(SuccessCode.STUDENT_SAVE_SUCCESS, studentInfoResDto);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentListResDto> studentFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sort", defaultValue = "studentId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("studentId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        StudentListResDto studentListResDto = studentService.studentFindAll(pageable);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, studentListResDto);
    }

    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studnetFindOne(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.studentFindOne(studentId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, studentInfoResDto);
    }

    @PatchMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studentUpdate(@PathVariable("studentId") Long studentId, @RequestBody @Valid StudentUpdateReqDto studentUpdateReqDto) {
        final StudentInfoResDto studentInfoResDto = studentService.studentUpdate(studentId, studentUpdateReqDto);

        return BaseResponse.success(SuccessCode.STUDENT_UPDATE_SUCCESS, studentInfoResDto);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studentDelete(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.StudentDelete(studentId);

        return BaseResponse.success(SuccessCode.STUDENT_DELETE_SUCCESS, studentInfoResDto);
    }

    

}
