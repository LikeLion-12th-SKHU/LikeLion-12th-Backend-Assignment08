package org.likelion.likelionassignmentcrud.customer.application;

import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerSaveReqDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.response.CustomerInfoResDto;
import org.likelion.likelionassignmentcrud.customer.api.dto.response.CustomerListResDto;
import org.likelion.likelionassignmentcrud.customer.domain.Customer;
import org.likelion.likelionassignmentcrud.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Transactional
    public void customerSave(CustomerSaveReqDto customerSaveReqDto) {

        Customer customer = Customer.builder()
                .name(customerSaveReqDto.name())
                .age(customerSaveReqDto.age())
                .part(customerSaveReqDto.part())
                .build();

        customerRepository.save(customer);

    }

    public CustomerListResDto customerFindAll() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerInfoResDto> customerInfoResDtoList = customers.stream()
                .map(CustomerInfoResDto::from)
                .toList();

        return CustomerListResDto.from(customerInfoResDtoList);
    }

    public CustomerInfoResDto customerFindOne(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);

        return CustomerInfoResDto.from(customer);
    }

    // update
    @Transactional
    public void customerUpdate(Long customerId, CustomerUpdateReqDto customerUpdateReqDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);
        customer.update(customerUpdateReqDto);
    }

    // delete
    @Transactional
    public void customerDelete(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);
        customerRepository.delete(customer);
    }

}
