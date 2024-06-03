package org.likelion.likelionassignmentcrud.orders.domain.repository;

import java.util.List;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Orders, Long>{
    List<Orders> findByfood(Food food);

}
