// 테이블에 접근 하기 위한 클래스 = repository

package org.likelion.assignmentvalid.product.domain.repository;

import org.likelion.assignmentvalid.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 필요 없음 : JpaRepository에 이미 있음 (빈 등록해줌)
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
}
