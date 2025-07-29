package com.example.datn.repository;

import com.example.datn.Model.order_items;
import com.example.datn.Model.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ordersRepository extends JpaRepository<orders, String> {
    List<orders> findByStatusNot(String orderId);
}
