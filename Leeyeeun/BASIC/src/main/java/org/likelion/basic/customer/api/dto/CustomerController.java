package org.likelion.basic.customer.api.dto;

import jakarta.validation.Valid;
import org.likelion.basic.common.dto.BaseResponse;
import org.likelion.basic.common.error.SuccessCode;
import org.likelion.basic.customer.api.dto.request.CustomerSaveReqDto;
import org.likelion.basic.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.basic.customer.api.dto.response.CustomerInfoResDto;
import org.likelion.basic.customer.api.dto.response.CustomerListResDto;
import org.likelion.basic.customer.application.CustomerService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController (CustomerService customerService) { this.customerService = customerService; }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CustomerInfoResDto> customerSave(@RequestBody @Valid CustomerSaveReqDto customerSaveReqDto) {
        CustomerInfoResDto customerInfoResDto = customerService.customerSave(customerSaveReqDto);
        return BaseResponse.success(SuccessCode.MEMBER_SAVE_SUCCESS, customerInfoResDto);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CustomerListResDto> customerFindAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue = "memberId,asc") String sort)
    {
            Pageable pageable;
            if (sort.isEmpty()) {
                pageable = PageRequest.of(page, size, Sort.by("memberId").ascending());
            } else {
                String[] sortParams = sort.split(",");
                Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
                pageable = PageRequest.of(page, size, sortOrder);
            }
        CustomerListResDto customerListResDto = customerService.customerFindAll(pageable);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, customerListResDto);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CustomerInfoResDto> customerFindOne(@PathVariable("customerId") Long customerId) {
        CustomerInfoResDto customerInfoResDto = customerService.customerFindOne(customerId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, customerInfoResDto);
    }

    @PatchMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CustomerInfoResDto> customerUpdate(@PathVariable("customerId") Long customerId,
                                                 @RequestBody @Valid CustomerUpdateReqDto customerUpdateReqDto) {
        CustomerInfoResDto customerInfoResDto = customerService.customerUpdate(customerId, customerUpdateReqDto);
        return BaseResponse.success(SuccessCode.CUSTOMER_UPDATE_SUCCESS, customerInfoResDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CustomerInfoResDto> customerDelete(@PathVariable("customerId") Long customerId) {
        CustomerInfoResDto customerInfoResDto = customerService.customerDelete(customerId);
        return BaseResponse.success(SuccessCode.CUSTOMER_DELETE_SUCCESS, customerInfoResDto);
    }

}
