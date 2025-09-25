package com.example.datn.repository;

import com.example.datn.Model.Discounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface discountsRepository extends JpaRepository<Discounts, String> {
    List<Discounts> findByIdContainingOrDescriptionContainingIgnoreCase(String id, String description);


    Page<Discounts> findByDiscountType(String type, Pageable pageable);

    @Query("SELECT d FROM Discounts d " +
            "WHERE d.status = 'active' " +
            "AND CURRENT_DATE BETWEEN d.startDate AND d.endDate")
    List<Discounts> findAllValidDiscounts();

    @Query("SELECT d FROM Discounts d WHERE d.status = 'active' AND CURRENT_DATE BETWEEN d.startDate AND d.endDate")
    List<Discounts> findValidDiscountsForProduct(@Param("productId") String productId);

}
