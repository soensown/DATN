package com.example.datn.repository;

import com.example.datn.Model.brands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface brandsRepository extends JpaRepository<brands, Integer> {
    Page<brands> findByBrandNameContainingIgnoreCase(String keyword, Pageable pageable);
}