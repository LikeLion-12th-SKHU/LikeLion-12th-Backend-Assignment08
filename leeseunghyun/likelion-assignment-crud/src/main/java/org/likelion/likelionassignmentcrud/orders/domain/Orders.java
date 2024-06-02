package org.likelion.likelionassignmentcrud.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderss_id")
    private Long ordersId;

    private String shippingAddress;
    private String paymentInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder
    private Orders(String shippingAddress, String paymentInfo, Food food) {
        this.shippingAddress = shippingAddress;
        this.paymentInfo = paymentInfo;
        this.food = food;
    }
    public void update(OrdersUpdateReqDto ordersUpdateReqDto){
        this.shippingAddress = ordersUpdateReqDto.shippingAddress();
        this.paymentInfo = ordersUpdateReqDto.paymentInfo();
    }

}