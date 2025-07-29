package com.example.datn.repository;

import com.example.datn.Model.sizes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sizesRepository extends JpaRepository<sizes, Integer> {
    boolean existsBySizeIgnoreCase(String size);
    Page<sizes> findBySizeContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT s FROM sizes s JOIN product_details pd ON pd.size = s WHERE pd.product.id = :productId")
    List<sizes> findByProductId(@Param("productId") String productId);
}