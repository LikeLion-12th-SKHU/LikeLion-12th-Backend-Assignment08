package org.likelion.likelioncrud.consumer.domain;

import  jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelioncrud.consumer.api.dto.request.ConsumerUpdateReqDto;
import org.likelion.likelioncrud.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long consumerId;

    private String name;
    private int age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Part part;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    private Consumer(String name, int age, String email, Part part) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.part = part;
    }

    public void update(ConsumerUpdateReqDto consumerUpdateReqDto) {
        this.name = consumerUpdateReqDto.name();
        this.age = consumerUpdateReqDto.age();
        this.email = consumerUpdateReqDto.email();
    }
}
