package org.likelion.likelionassignmentcrud.users.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    private String name;
    private String phoneNumber;

    // email추가
    private String email;

    @Enumerated(EnumType.STRING)
    private PayOption option;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    public Users(String name, String phoneNumber, String email, PayOption option) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.option = option;
    }

    public void update(UsersUpdateReqDto usersUpdateReqDto) {
        this.name = usersUpdateReqDto.name();
        this.phoneNumber = usersUpdateReqDto.phoneNumber();
        this.email = usersUpdateReqDto.email();
    }
}