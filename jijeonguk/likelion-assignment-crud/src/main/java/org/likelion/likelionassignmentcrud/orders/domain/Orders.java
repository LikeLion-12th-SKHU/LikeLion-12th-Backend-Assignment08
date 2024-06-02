package org.likelion.likelionassignmentcrud.orders.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.likelion.likelionassignmentcrud.users.domain.PayOption;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    private String address;
    private Long price ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;
    @Builder
    private Orders(String address, Long price , Users users ) {
        this.address = address;
        this.price = price;
        this.users = users;
    }

    public void update(OrdersUpdateReqDto ordersUpdateReqDto) {
        this.address = ordersUpdateReqDto.address();
        this.price = ordersUpdateReqDto.price();
    }
}