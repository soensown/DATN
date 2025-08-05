package com.example.datn.repository;

import com.example.datn.Model.Product_details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface product_detailsRepository extends JpaRepository<Product_details, String> {
    Page<Product_details> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);
    // thử commit lại
}

