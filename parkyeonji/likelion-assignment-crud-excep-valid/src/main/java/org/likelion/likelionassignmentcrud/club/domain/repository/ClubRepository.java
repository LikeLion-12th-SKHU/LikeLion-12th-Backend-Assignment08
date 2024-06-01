package org.likelion.likelionassignmentcrud.club.domain.repository;

import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findAll(Pageable pageable);
}
