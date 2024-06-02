package org.likelion.jangsu1.Student.api;

import jakarta.validation.Valid;
import org.likelion.jangsu1.Student.api.request.StudentSaveReqDto;
import org.likelion.jangsu1.Student.api.request.StudentUpdateReqDto;
import org.likelion.jangsu1.Student.api.response.StudentInfoResDto;
import org.likelion.jangsu1.Student.api.response.StudentListResDto;
import org.likelion.jangsu1.Student.application.StudentService;
import org.likelion.jangsu1.common.dto.BaseResponse;
import org.likelion.jangsu1.common.error.SuccessCode;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 생성하기
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<StudentInfoResDto> studentSave(@RequestBody @Valid StudentSaveReqDto studentSaveReqDto) {
        StudentInfoResDto studentInfoResDto = studentService.studentSave(studentSaveReqDto);
        return BaseResponse.success(SuccessCode.STUDENT_SAVE_SUCCESS, studentInfoResDto);
    }

    // 한명 불러오기
    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studentFindOne(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.studentFindOne(studentId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, studentInfoResDto);
    }

    // 전부 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentListResDto> studentFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
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

    // 수정하기
    @PatchMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studentUpdate(@PathVariable("studentId") Long studentId,
                                                         @RequestBody @Valid StudentUpdateReqDto studentUpdateReqDto) {
        StudentInfoResDto studentInfoResDto = studentService.studentUpdate(studentId, studentUpdateReqDto);
        return BaseResponse.success(SuccessCode.STUDENT_UPDATE_SUCCESS, studentInfoResDto);
    }

    // 삭제하기
    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<StudentInfoResDto> studentDelete(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.studentDelete(studentId);
        return BaseResponse.success(SuccessCode.STUDENT_DELETE_SUCCESS, studentInfoResDto);
    }
}