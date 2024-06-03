package org.likelion.assignmentvalid.orders.domain.repository;

import org.likelion.assignmentvalid.orders.domain.Orders;
import org.likelion.assignmentvalid.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByProduct(Product product);
}
