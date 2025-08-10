package com.example.datn.repository;

import com.example.datn.Model.Colors;
import com.example.datn.Model.ProductDetails;
import com.example.datn.Model.Products;
import com.example.datn.Model.Sizes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface product_detailsRepository extends JpaRepository<ProductDetails, String> {
    Page<ProductDetails> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    List<ProductDetails> findByProduct(Products product);

    @Query("SELECT DISTINCT pd.color FROM ProductDetails pd WHERE pd.product.id = :productId")
    List<Colors> findColorsByProductId(@Param("productId") String productId);

    @Query("SELECT DISTINCT pd.size FROM ProductDetails pd WHERE pd.product.id = :productId")
    List<Sizes> findSizesByProductId(@Param("productId") String productId);
}

