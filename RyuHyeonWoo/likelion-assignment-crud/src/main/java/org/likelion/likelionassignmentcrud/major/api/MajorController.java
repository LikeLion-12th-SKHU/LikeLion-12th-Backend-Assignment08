package org.likelion.likelionassignmentcrud.major.api;

import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorSaveReqDto;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.major.api.dto.response.MajorListResDto;
import org.likelion.likelionassignmentcrud.major.application.MajorService;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.application.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/major")
public class MajorController {

    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> majorSave(@RequestBody MajorSaveReqDto majorSaveReqDto) {
        majorService.majorSave(majorSaveReqDto);
        return new ResponseEntity<>("전공 저장", HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<MajorListResDto> myMajorFindAll(@PathVariable("studentId") Long studentId) {
        MajorListResDto majorListResDto = majorService.majorFindStudent(studentId);
        return new ResponseEntity<>(majorListResDto, HttpStatus.OK);
    }

    //@PutMapping = 데이터 전부 변경
    //@PatchMapping = 데이터 일부 변경

    @PatchMapping("/{majorId}")
    public ResponseEntity<String> majorUpdate(@PathVariable("majorId") Long majorId, @RequestBody MajorUpdateReqDto majorUpdateReqDto) {
        majorService.majorUpdate(majorId, majorUpdateReqDto);
        return new ResponseEntity<>("전공 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{majorId}")
    public ResponseEntity<String> majorDelete(@PathVariable("majorId") Long majorId) {
        majorService.majorDelete(majorId);

        return new ResponseEntity<>("전공 삭제", HttpStatus.OK);
    }
}
