package org.likelion.basic.customer.application;

import org.springframework.data.domain.Page;
import org.likelion.basic.common.error.ErrorCode;
import org.likelion.basic.common.exception.NotFoundException;
import org.likelion.basic.customer.api.dto.request.CustomerSaveReqDto;
import org.likelion.basic.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.basic.customer.api.dto.response.CustomerInfoResDto;
import org.likelion.basic.customer.api.dto.response.CustomerListResDto;
import org.likelion.basic.customer.domain.Customer;
import org.likelion.basic.customer.domain.repository.CustomerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Transactional
    public CustomerInfoResDto customerSave(CustomerSaveReqDto customerSaveReqDto) {

        Customer customer = Customer.builder()
                .name(customerSaveReqDto.name())
                .age(customerSaveReqDto.age())
                .email(customerSaveReqDto.email())
                .part(customerSaveReqDto.part())
                .build();

        customerRepository.save(customer);

        return CustomerInfoResDto.from(customer);
    }

    public CustomerListResDto customerFindAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        List<CustomerInfoResDto> customerInfoResDtoList = customers.stream()
                .map(CustomerInfoResDto::from)
                .collect(Collectors.toList());

        return CustomerListResDto.from(customerInfoResDtoList);
    }

    public CustomerInfoResDto customerFindOne(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION, ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION.getMessage()
                                                                                        + customerId));

        return CustomerInfoResDto.from(customer);
    }

    // update
    @Transactional
    public CustomerInfoResDto customerUpdate(Long customerId, CustomerUpdateReqDto customerUpdateReqDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION, ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION.getMessage()
                                                                                        + customerId));
        customer.update(customerUpdateReqDto);
        return CustomerInfoResDto.from(customer);
    }

    // delete
    @Transactional
    public CustomerInfoResDto customerDelete(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION, ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION.getMessage()
                                                                                        + customerId));
        customerRepository.delete(customer);
        return CustomerInfoResDto.from(customer);
    }

}
