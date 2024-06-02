package org.likelion.likelionassignmentcrud.orders.domain.repository;

import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUsers(Users users);
}
