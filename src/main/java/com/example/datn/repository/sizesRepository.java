package com.example.datn.repository;

import com.example.datn.Model.Sizes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sizesRepository extends JpaRepository<Sizes, Integer> {
    boolean existsBySizeIgnoreCase(String size);
    Page<Sizes> findBySizeContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT s FROM Sizes s JOIN ProductDetails pd ON pd.size = s WHERE pd.product.id = :productId")
    List<Sizes> findByProductId(@Param("productId") String productId);
}