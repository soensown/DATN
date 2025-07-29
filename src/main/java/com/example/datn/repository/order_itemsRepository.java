package com.example.datn.repository;

import com.example.datn.Model.order_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface order_itemsRepository extends JpaRepository<order_items, Integer> {
    List<order_items> findByOrder_Id(String orderId);
}
