package org.likelion.likelionassignmentcrud.customer.api.dto;

import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerSaveReqDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.response.CustomerInfoResDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.response.CustomerListResDto;
import org.likelion.likelionassignmentcrud.customer.application.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController (CustomerService customerService) { this.customerService = customerService; }

    @PostMapping("/save")
    public ResponseEntity<String> customerSave(@RequestBody CustomerSaveReqDto customerSaveReqDto) {
        customerService.customerSave(customerSaveReqDto);
        return new ResponseEntity<>("고객 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomerListResDto> customerFindAll() {
        CustomerListResDto customerListResDto = customerService.customerFindAll();


        return new ResponseEntity<>(customerListResDto, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerInfoResDto> customerFindOne(@PathVariable("customerId") Long customerId) {
        CustomerInfoResDto customerInfoResDto = customerService.customerFindOne(customerId);
        return new ResponseEntity<>(customerInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<String> customerUpdate(@PathVariable("customerId") Long customerId,
                                                 @RequestBody CustomerUpdateReqDto customerUpdateReqDto) {
        customerService.customerUpdate(customerId, customerUpdateReqDto);
        return new ResponseEntity<>("고객 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> customerDelete(@PathVariable("customerId") Long customerId) {
        customerService.customerDelete(customerId);
        return new ResponseEntity<>("고객 삭제", HttpStatus.OK);
    }

}
