package org.likelion.likelionassignmentcrud.shop.domain.repository;

import org.likelion.likelionassignmentcrud.customer.domain.Customer;
import org.likelion.likelionassignmentcrud.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByCustomer(Customer customer);
}
