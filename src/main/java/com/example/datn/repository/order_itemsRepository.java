package com.example.datn.repository;

import com.example.datn.Model.Order_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface order_itemsRepository extends JpaRepository<Order_items, Integer> {
    List<Order_items> findByOrder_Id(String orderId);
}
