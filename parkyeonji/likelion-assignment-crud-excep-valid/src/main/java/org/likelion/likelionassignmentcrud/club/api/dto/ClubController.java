package org.likelion.likelionassignmentcrud.club.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubSaveReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubUpdateReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubInfoResDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubListResDto;
import org.likelion.likelionassignmentcrud.club.application.ClubService;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ClubInfoResDto> clubSave(@RequestBody @Valid ClubSaveReqDto clubSaveReqDto) {
        ClubInfoResDto clubInfoResDto = clubService.clubSave(clubSaveReqDto);
        return BaseResponse.success(SuccessCode.CLUB_SAVE_SUCCESS, clubInfoResDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ClubListResDto> clubFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "memberId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("clubId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        ClubListResDto clubListResDto = clubService.clubFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, clubListResDto);
    }

    @GetMapping("/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ClubInfoResDto> clubFindOne(@PathVariable("clubId") Long clubId) {
        ClubInfoResDto clubInfoResDto = clubService.clubFindOne(clubId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, clubInfoResDto);
    }

    @PatchMapping("/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ClubInfoResDto> clubUpdate(@PathVariable("clubId") Long clubId,
                                             @RequestBody @Valid ClubUpdateReqDto clubUpdateReqDto) {
        ClubInfoResDto clubInfoResDto = clubService.clubUpdate(clubId, clubUpdateReqDto);
        return BaseResponse.success(SuccessCode.CLUB_UPDATE_SUCCESS, clubInfoResDto);
    }

    @DeleteMapping("/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ClubInfoResDto> clubDelete(@PathVariable("clubId") Long clubId) {
        ClubInfoResDto clubInfoResDto = clubService.clubDelete(clubId);
        return BaseResponse.success(SuccessCode.CLUB_DELETE_SUCCESS, clubInfoResDto);
    }
}
