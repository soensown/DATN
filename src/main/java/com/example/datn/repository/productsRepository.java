package com.example.datn.repository;

import com.example.datn.Model.product_details;
import com.example.datn.Model.products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productsRepository extends JpaRepository<products, String> {
    Page<products> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

    List<products> findTop10ByOrderByCreatedDateDesc();


}
