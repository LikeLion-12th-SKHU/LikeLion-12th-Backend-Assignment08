package org.likelion.likelionassignmentcrud.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.likelionassignmentcrud.shop.domain.Shop;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Part part;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shop> shops = new ArrayList<>();

    @Builder

    public Customer(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }

    public void update(CustomerUpdateReqDto customerUpdateReqDto) {
        this.name = customerUpdateReqDto.name();
        this.age = customerUpdateReqDto.age();
    }
}
