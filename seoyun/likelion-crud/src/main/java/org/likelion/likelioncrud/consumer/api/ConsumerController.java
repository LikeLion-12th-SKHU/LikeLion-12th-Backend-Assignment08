package org.likelion.likelioncrud.consumer.api;


import jakarta.validation.Valid;
import org.likelion.likelioncrud.common.dto.BaseResponse;
import org.likelion.likelioncrud.common.error.SuccessCode;
import org.likelion.likelioncrud.consumer.api.dto.request.ConsumerSaveReqDto;
import org.likelion.likelioncrud.consumer.api.dto.request.ConsumerUpdateReqDto;
import org.likelion.likelioncrud.consumer.api.dto.response.ConsumerInfoResDto;
import org.likelion.likelioncrud.consumer.api.dto.response.ConsumerListResDto;
import org.likelion.likelioncrud.consumer.application.ConsumerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    //멤버 저장
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ConsumerInfoResDto> consumerSave(@RequestBody @Valid ConsumerSaveReqDto consumerSaveReqDto) {
        ConsumerInfoResDto consumerInfoResDto = consumerService.consumerSave(consumerSaveReqDto);
        return BaseResponse.success(SuccessCode.CONSUMER_SAVE_SUCCESS, consumerInfoResDto);
    }

    //멤버 전체 조회
    @GetMapping("/consumers")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ConsumerListResDto> consumerFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "consumerId,desc") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size);

        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("consumerId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }

        ConsumerListResDto consumerListResDto = consumerService.consumerFindAll((java.awt.print.Pageable) pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, consumerListResDto);
    }

    //멤버 id에 따라 멤버 한 개 조회
    @GetMapping("/{consumerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ConsumerInfoResDto> consumerFindOne(@PathVariable("consumerId") Long consumerId) {
        ConsumerInfoResDto consumerInfoResDto = consumerService.consumerFindOne(consumerId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, consumerInfoResDto);
    }

    //수정
    @PatchMapping("/{consumerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ConsumerInfoResDto> consumerUpdate(@PathVariable("consumerId") Long consumerId,
                                                           @RequestBody @Valid ConsumerUpdateReqDto consumerUpdateReqDto) {
        ConsumerInfoResDto consumerInfoResDto = consumerService.consumerUpdate(consumerId, consumerUpdateReqDto);
        return BaseResponse.success(SuccessCode.CONSUMER_UPDATE_SUCCESS, consumerInfoResDto);
    }

    //삭제
    @DeleteMapping("/{consumerId}")
    public BaseResponse<ConsumerInfoResDto> consumerDelete(@PathVariable("consumerId") Long consumerId) {
        ConsumerInfoResDto consumerInfoResDto = consumerService.consumerDelete(consumerId);
        return BaseResponse.success(SuccessCode.CONSUMER_DELETE_SUCCESS, consumerInfoResDto);
    }
}