package com.example.datn.repository;

import com.example.datn.Model.Cart_items;
import com.example.datn.Model.ProductDetails;
import com.example.datn.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface cart_itemsRepository extends JpaRepository<Cart_items, Integer> {
    List<Cart_items> findByUser(Users user);

    Optional<Cart_items> findById(String id);

    Cart_items findByUserAndProductDetails(Users user, ProductDetails productDetails);
}
