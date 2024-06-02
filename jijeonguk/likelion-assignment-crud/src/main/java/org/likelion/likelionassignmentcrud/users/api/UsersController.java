package org.likelion.likelionassignmentcrud.users.api;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersSaveReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersUpdateReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersInfoResDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersListResDto;
import org.likelion.likelionassignmentcrud.users.application.UsersService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private  final UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    // 고객 저장
    // 수정
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<UsersInfoResDto> usersSave(@RequestBody @Valid UsersSaveReqDto usersSaveReqDto) {
        UsersInfoResDto usersInfoResDto = usersService.usersSave(usersSaveReqDto);
        return BaseResponse.success(SuccessCode.USERS_SAVE_SUCCESS, usersInfoResDto);
    }

    // 고객 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UsersListResDto> usersFindAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue = "usersId,asc") String sort
    ) {
        Pageable pageable;
        if(sort.isEmpty()){
            // default 정렬의 기준은 uesrsId, 오름차순이고 Pageable 객체를 생성해준다.
            // 하지만 sort 파라미터가 제공된 경우, 이를 ,로 분리하여 정렬 기준과 정렬 방향을 추출한다.
            pageable = PageRequest.of(page, size, Sort.by("usersId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        UsersListResDto usersListResDto = usersService.usersFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, usersListResDto);
    }

    // 고객 id에 따라 고객 한개 조회
    @GetMapping("/{usersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UsersInfoResDto> usersFindOne(@PathVariable("usersId")Long usersId) {
        UsersInfoResDto usersInfoResDto = usersService.usersFindOne(usersId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, usersInfoResDto);

    }

    // 수정
    // 주문 수정
    @PatchMapping("/{usersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UsersInfoResDto> usersUpdate(@PathVariable("usersId") Long usersId,
                                              @RequestBody @Valid UsersUpdateReqDto usersUpdateReqDto) {

        final UsersInfoResDto usersInfoResDto = usersService.usersUpdate(usersId, usersUpdateReqDto);
        return BaseResponse.success(SuccessCode.USERS_UPDATE_SUCCESS, usersInfoResDto);
    }

    @DeleteMapping("/{usersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UsersInfoResDto> usersDelete(@PathVariable("usersId") Long uesrsId) {
        UsersInfoResDto usersInfoResDto = usersService.usersDelete(uesrsId);
        return BaseResponse.success(SuccessCode.USERS_DELETE_SUCCESS, usersInfoResDto);

    }
}
