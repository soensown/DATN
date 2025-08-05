package com.example.datn.repository;

import com.example.datn.Model.Discounts;
import com.example.datn.Model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface materialRepository extends JpaRepository<Material, Integer> {
    @Query("SELECT c FROM Material c WHERE c.materialName LIKE %?1% ")
    List<Discounts> searchByType(String keyword);

    Page<Material> findByMaterialName(String trim, Pageable pageable);
}
