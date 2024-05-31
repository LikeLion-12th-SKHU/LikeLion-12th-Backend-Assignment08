package org.likelion.likelionassignmentcrud.developer.api;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperSaveReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperUpdateReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperInfoResDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperListResDto;
import org.likelion.likelionassignmentcrud.developer.application.DeveloperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<DeveloperInfoResDto> developerSave(@RequestBody @Valid DeveloperSaveReqDto developerSaveReqDto) {
        DeveloperInfoResDto developerInfoResDto = developerService.developerSave(developerSaveReqDto);

        return BaseResponse.success(SuccessCode.DEVELOPER_SAVE_SUCCESS, developerInfoResDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DeveloperListResDto> developerFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "developerId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("developerId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        DeveloperListResDto developerListResDto = developerService.developerFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, developerListResDto);
    }

    @GetMapping("/{developerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DeveloperInfoResDto> developerFindOne(@PathVariable("developerId") Long developerId) {
        DeveloperInfoResDto developerInfoResDto = developerService.developerFindOne(developerId);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, developerInfoResDto);
    }

    @PatchMapping("/{developerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DeveloperInfoResDto> developerUpdate(@PathVariable("developerId") Long developerId, @RequestBody @Valid DeveloperUpdateReqDto developerUpdateReqDto) {
        DeveloperInfoResDto developerInfoResDto = developerService.developerUpdate(developerId, developerUpdateReqDto);

        return BaseResponse.success(SuccessCode.DEVELOPER_UPDATE_SUCCESS, developerInfoResDto);
    }

    @DeleteMapping("/{developerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<DeveloperInfoResDto> developerDelete(@PathVariable("developerId") Long developerId) {
        DeveloperInfoResDto developerInfoResDto = developerService.developerDelete(developerId);

        return BaseResponse.success(SuccessCode.DEVELOPER_DELETE_SUCCESS, developerInfoResDto);
    }
}
