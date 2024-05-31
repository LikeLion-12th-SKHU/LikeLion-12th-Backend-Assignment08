package org.likelion.likelionassignmentcrud.developer.domain.repository;

import org.likelion.likelionassignmentcrud.developer.domain.Developer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Page<Developer> findAll(Pageable pageable);
    Optional<Developer> findByName(String name);
}
