package org.likelion.likelionassignmentcrud.lecture.api;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.lecture.api.dto.request.LectureSaveReqDto;
import org.likelion.likelionassignmentcrud.lecture.api.dto.request.LectureUpdateReqDto;
import org.likelion.likelionassignmentcrud.lecture.api.dto.response.LectureInfoResDto;
import org.likelion.likelionassignmentcrud.lecture.api.dto.response.LectureListResDto;
import org.likelion.likelionassignmentcrud.lecture.application.LectureService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    // 강의 저장
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<LectureInfoResDto> lectureSave(@RequestBody @Valid LectureSaveReqDto lectureSaveReqDto) {
        LectureInfoResDto lectureInfoResDto = lectureService.lectureSave(lectureSaveReqDto);
        return BaseResponse.success(SuccessCode.LECTURE_SAVE_SUCCESS, lectureInfoResDto);
    }

    // 강의 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<LectureListResDto> lectureFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "lectureId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {   // 강의 Id 오름차순으로 정렬
            pageable = PageRequest.of(page, size, Sort.by("lectureId").ascending());
        } else {
            String[] sortParams = sort.split(",");  // 쉼표 기준으로 정렬
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]); // 정렬 방향, 정렬 기준 추출
            pageable = PageRequest.of(page, size, sortOrder);
        }
        LectureListResDto lectureListResDto = lectureService.lectureFindAll(pageable);  // professorservice의 findall 메소드 호출해서 전체 교수(리스트) 조회
        return BaseResponse.success(SuccessCode.GET_SUCCESS, lectureListResDto);  //성공 응답 반환(SuccessCode)
    }

    // 교수 id에 따라 강의 전체 조회
    @GetMapping("/{professorId}/lectures")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<LectureListResDto> myLectureFindAll(@PathVariable("professorId") Long professorId) {
        LectureListResDto lectureListResDto = lectureService.lectureFindProfessor(professorId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, lectureListResDto);
    }

    // 강의 Id에 따라 강의 한 개 조회
    @GetMapping("/{lectureId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<LectureInfoResDto> lectureFindById(@PathVariable("lectureId") Long lectureId) {
        LectureInfoResDto lectureInfoResDto = lectureService.lectureFindById(lectureId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, lectureInfoResDto);
    }

    // 강의 update
    @PatchMapping("{lectureId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<LectureInfoResDto> lectureUpdate(@PathVariable("lectureId") Long lectureId,
                                                @RequestBody @Valid LectureUpdateReqDto lectureUpdateReqDto) {
        LectureInfoResDto lectureInfoResDto = lectureService.lectureUpdate(lectureId, lectureUpdateReqDto);
        return BaseResponse.success(SuccessCode.LECTURE_UPDATE_SUCCESS, lectureInfoResDto);
    }

    // 강의 delete
    @DeleteMapping("{lectureId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<LectureInfoResDto> lectureDelete(@PathVariable("lectureId") Long lectureId) {
        LectureInfoResDto lectureInfoResDto = lectureService.lectureDelete(lectureId);
        return BaseResponse.success(SuccessCode.LECTURE_DELETE_SUCCESS, lectureInfoResDto);
    }
}
