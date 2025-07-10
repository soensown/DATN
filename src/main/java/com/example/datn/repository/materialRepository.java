package com.example.datn.repository;

import com.example.datn.Model.discounts;
import com.example.datn.Model.material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface materialRepository extends JpaRepository<material, Integer> {
    @Query("SELECT c FROM material c WHERE c.materialName LIKE %?1% ")
    List<discounts> searchByType(String keyword);

    Page<material> findByMaterialName(String trim, Pageable pageable);
}
