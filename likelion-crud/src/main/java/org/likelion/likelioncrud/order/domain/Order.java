package org.likelion.likelionassignmentcrud.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.consumer.domain.Consumer;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId; //주문번호

    private Long price; //가격
    private String name; //물건이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @Builder
    private Order(Long price, String name, Consumer consumer) {
        this.price = price;
        this.name = name;
        this.consumer = consumer;
    }

    public void update(OrderUpdateReqDto orderUpdateReqDto){
        this.price = orderUpdateReqDto.price();
        this.name = orderUpdateReqDto.name();
    }
}
