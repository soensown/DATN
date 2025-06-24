package com.example.datn.repository;

import com.example.datn.Model.sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sizesRepository extends JpaRepository<sizes, Integer> {
}
