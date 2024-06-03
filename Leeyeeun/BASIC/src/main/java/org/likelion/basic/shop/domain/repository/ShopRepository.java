package org.likelion.basic.shop.domain.repository;


import org.likelion.basic.customer.domain.Customer;
import org.likelion.basic.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByCustomer(Customer customer);
}
