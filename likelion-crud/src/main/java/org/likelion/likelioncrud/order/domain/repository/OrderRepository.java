package org.likelion.likelionassignmentcrud.order.domain.repository;

import org.likelion.likelionassignmentcrud.consumer.domain.Consumer;
import org.likelion.likelionassignmentcrud.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByConsumer(Consumer consumer);
}