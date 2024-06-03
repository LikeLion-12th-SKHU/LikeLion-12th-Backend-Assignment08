package org.likelion.likelioncrud.consumer.domain.repository;


import org.likelion.likelioncrud.consumer.domain.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Page<Consumer> findAll(Pageable pageable);

}