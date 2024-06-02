package org.likelion.jangsu1.Rent.api;

import jakarta.validation.Valid;
import org.likelion.jangsu1.Rent.api.request.RentSaveReqDto;
import org.likelion.jangsu1.Rent.api.request.RentUpdateReqDto;
import org.likelion.jangsu1.Rent.api.response.RentInfoResDto;
import org.likelion.jangsu1.Rent.api.response.RentListResDto;
import org.likelion.jangsu1.Rent.appication.RentService;
import org.likelion.jangsu1.common.dto.BaseResponse;
import org.likelion.jangsu1.common.error.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rents")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    // 저장하기
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<RentInfoResDto> rentSave(@RequestBody @Valid RentSaveReqDto rentSaveReqDto) {
        RentInfoResDto rentInfoResDto = rentService.rentSave(rentSaveReqDto);
        return BaseResponse.success(SuccessCode.RENT_SAVE_SUCCESS, rentInfoResDto);
    }

    // 대출 현황 불러오기
    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<RentListResDto> rentFindAll(@PathVariable("studentId") Long studentId) {
        RentListResDto rentListResDto = rentService.rentFindAll(studentId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, rentListResDto);
    }

    // 대출 현황 수정하기
    @PatchMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<RentInfoResDto> rentUpdate(@PathVariable("studentId") Long rentId, @RequestBody @Valid RentUpdateReqDto rentUpdateReqDto) {
        RentInfoResDto rentInfoResDto = rentService.rentUpdate(rentId, rentUpdateReqDto);
        return BaseResponse.success(SuccessCode.RENT_UPDATE_SUCCESS, rentInfoResDto);
    }

    // 삭제하기
    @DeleteMapping("/{rentId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<RentInfoResDto> rentDelete(@PathVariable("rentId") Long rentId) {
        RentInfoResDto rentInfoResDto = rentService.rentDelete(rentId);
        return BaseResponse.success(SuccessCode.RENT_DELETE_SUCCESS, rentInfoResDto);
    }

    //전체 삭제하기
    @DeleteMapping("/{studentId}/Delete")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<RentInfoResDto> rentDeleteAll(@PathVariable("studentId") Long studentId) {
        RentInfoResDto rentInfoResDto = rentService.rentDeleteAll(studentId);
        return BaseResponse.success(SuccessCode.RENT_DELETE_SUCCESS, rentInfoResDto);
    }
}
