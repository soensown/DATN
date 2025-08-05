package com.example.datn.repository;

import com.example.datn.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ordersRepository extends JpaRepository<Orders, String> {
    List<Orders> findByStatusNot(String orderId);
}
