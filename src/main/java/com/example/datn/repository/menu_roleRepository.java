package com.example.datn.repository;

import com.example.datn.Model.Menu_role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menu_roleRepository extends JpaRepository<Menu_role,Integer> {
}
