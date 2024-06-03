// Entity 역할
// 사용자는 이름, 나이, 파트, 게시물들

package org.likelion.assignmentvalid.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.assignmentvalid.orders.domain.Orders;
import org.likelion.assignmentvalid.product.api.dto.request.ProductUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 강의자료 참고하기
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;
    private int price;

    @Enumerated(EnumType.STRING) // String 타입으로 데이터베이스에 넣겠다는 의미
    private Part part;

    // 게시물들
    // 1대다(1사용자 - 여러 게시물)
    // mappedBy 주인 설정
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    private Product(String name, int price, Part part) { // Builder로만 생성자 접근할 수 있도록 private로 해줌
        this.name = name;
        this.price = price;
        this.part = part;
    }

    public void update(ProductUpdateReqDto productUpdateReqDto) {
        this.name = productUpdateReqDto.name();
        this.price = productUpdateReqDto.price();
    }

}
