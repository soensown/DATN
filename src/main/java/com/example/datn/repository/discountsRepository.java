package com.example.datn.repository;

import com.example.datn.Model.colors;
import com.example.datn.Model.discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface discountsRepository extends JpaRepository<discounts, Integer> {
    @Query("SELECT c FROM discounts c WHERE c.discountType LIKE %?1% ")
    List<discounts> searchByType(String keyword);
}
