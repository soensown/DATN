package com.example.datn.repository;

import com.example.datn.Model.product_details;
import com.example.datn.Model.products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productsRepository extends JpaRepository<products, Integer> {
}
