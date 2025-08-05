package com.example.datn.repository;

import com.example.datn.Model.Brands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface brandsRepository extends JpaRepository<Brands, Integer> {
    Page<Brands> findByBrandNameContainingIgnoreCase(String keyword, Pageable pageable);
}