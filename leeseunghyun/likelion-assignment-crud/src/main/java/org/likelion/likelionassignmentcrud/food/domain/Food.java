package org.likelion.likelionassignmentcrud.food.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    private String name; //음식 이름
    private int price ; //음식 가격
    private int foodPassword; //음식 비밀번호

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

    @Builder
    private Food(String name, int price ,int foodPassword, Type type) {
        this.name = name;
        this.price = price;
        this.foodPassword = foodPassword;
        this.type = type;
    }
    public void update(FoodUpdateReqDto foodUpdateReqDto) {
        this.name = foodUpdateReqDto.name();
        this.foodPassword = foodUpdateReqDto.foodPassword();
        this.type = foodUpdateReqDto.type();
    }
}

