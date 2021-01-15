package com.example.demo.repository;

import com.example.demo.entity.Fridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepository extends CrudRepository<Fridge, Long> {
}
