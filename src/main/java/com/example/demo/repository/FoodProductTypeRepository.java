package com.example.demo.repository;

import com.example.demo.entity.FoodProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodProductTypeRepository extends CrudRepository<FoodProductType, Long> {

}
