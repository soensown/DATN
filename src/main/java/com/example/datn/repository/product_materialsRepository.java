package com.example.datn.repository;

import com.example.datn.Model.Discounts;
import com.example.datn.Model.Product_materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface product_materialsRepository extends JpaRepository<Product_materials, Integer> {
    @Query("SELECT c FROM Discounts c WHERE c.discountType LIKE %?1% ")
    List<Discounts> searchByType(String keyword);
}
