package com.example.datn.repository;

import com.example.datn.Model.discounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface discountsRepository extends JpaRepository<discounts, Integer> {
    @Query("SELECT c FROM discounts c WHERE c.discountType LIKE %?1% ")
    List<discounts> searchByType(String keyword);
    Page<discounts> searchByDescription(String keyword, Pageable pageable);

    Page<discounts> findByDiscountType(String type, Pageable pageable);
}
