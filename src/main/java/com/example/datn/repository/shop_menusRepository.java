package com.example.datn.repository;

import com.example.datn.Model.Shop_menus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface shop_menusRepository extends CrudRepository<Shop_menus, Integer> {
}
