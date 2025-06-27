package com.example.datn.repository;

import com.example.datn.Model.sizes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sizesRepository extends JpaRepository<sizes, Integer> {
    boolean existsBySizeIgnoreCase(String size);
    Page<sizes> findBySizeContainingIgnoreCase(String keyword, Pageable pageable);
}