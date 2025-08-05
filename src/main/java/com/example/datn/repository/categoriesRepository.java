package com.example.datn.repository;

import com.example.datn.Model.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoriesRepository extends JpaRepository<Categories, String> {
    // thử commit lại
    Page<Categories> findByCategoryNameContainingIgnoreCase(String keyword, Pageable pageable);
}