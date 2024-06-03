package org.likelion.assignmentvalid.orders.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.assignmentvalid.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.assignmentvalid.product.domain.Product;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    private String custName; // 주문자 이름
    private String location; // 배송 지역

    @ManyToOne(fetch = FetchType.LAZY) // product와 연결
    @JoinColumn(name = "product_id") // product_id로 조인 받음
    private Product product;

    @Builder
    public Orders(String custName, String location, Product product) {
        this.custName = custName;
        this.location = location;
        this.product = product;
    }

    public void update(OrdersUpdateReqDto ordersUpdateReqDto) {
        this.custName = ordersUpdateReqDto.custName();
        this.location = ordersUpdateReqDto.location();
    }
}
