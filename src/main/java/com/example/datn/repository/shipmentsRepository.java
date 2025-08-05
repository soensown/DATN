package com.example.datn.repository;

import com.example.datn.Model.Shipments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface shipmentsRepository extends CrudRepository<Shipments, Integer> {
}
