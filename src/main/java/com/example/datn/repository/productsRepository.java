package com.example.datn.repository;

import com.example.datn.Model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productsRepository extends JpaRepository<Products, String> {
    Page<Products> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

    List<Products> findTop10ByOrderByCreatedDateDesc();


}
