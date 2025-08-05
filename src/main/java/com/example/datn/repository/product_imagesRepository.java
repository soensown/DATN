package com.example.datn.repository;

import com.example.datn.Model.Product_images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface product_imagesRepository extends JpaRepository<Product_images,String> {
}
