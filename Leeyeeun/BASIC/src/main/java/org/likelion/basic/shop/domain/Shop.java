package org.likelion.likelionassignmentcrud.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.customer.domain.Customer;
import org.likelion.likelionassignmentcrud.shop.api.dto.request.ShopUpdateReqDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long shopId;

    private String title;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Builder
    private Shop(String title, String contents, Customer customer) {
        this.title = title;
        this.contents = contents;
        this.customer = customer;
    }

    public void update(ShopUpdateReqDto shopUpdateReqDto) {
        this.title = shopUpdateReqDto.title();
        this.contents = shopUpdateReqDto.contents();
    }
}
