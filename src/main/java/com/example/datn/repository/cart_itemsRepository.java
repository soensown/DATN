package com.example.datn.repository;

import com.example.datn.Model.Cart_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cart_itemsRepository extends JpaRepository<Cart_items, Integer> {
}
