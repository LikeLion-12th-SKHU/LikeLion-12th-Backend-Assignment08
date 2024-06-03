package org.likelion.likelionassignmentcrud.consumer.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.consumer.api.dto.request.ConsumerUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.domain.Order;

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

    @Enumerated(EnumType.STRING)
    private Part part;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Builder
    private Consumer(String name, int age, Part part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }

    public void update(ConsumerUpdateReqDto consumerUpdateReqDto) {
        this.name = consumerUpdateReqDto.name();
        this.age = consumerUpdateReqDto.age();
    }
}
