package org.likelion.likelioncrud.order.domain.repository;

import org.likelion.likelioncrud.consumer.domain.Consumer;
import org.likelion.likelioncrud.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByConsumer(Consumer consumer);
}
