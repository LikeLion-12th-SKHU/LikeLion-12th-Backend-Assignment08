package org.likelion.likelionassignmentcrud.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private int date; //날짜

    @Enumerated(EnumType.STRING)
    private Item item; //품목

    private int quantity; //수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(int date, Item item, int quantity, Member member) {
        this.date = date;
        this.item = item;
        this.quantity = quantity;
        this.member = member;
    }

    public void update(OrderUpdateReqDto orderUpdateReqDto) {
        this.date = orderUpdateReqDto.date();
        this.item = orderUpdateReqDto.item();
        this.quantity = orderUpdateReqDto.quantity();
    }
}
