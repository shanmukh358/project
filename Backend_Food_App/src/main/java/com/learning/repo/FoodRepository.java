package com.learning.repo;

import com.learning.entity.Food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    public Page<Food> findAllByFoodType(String foodType, PageRequest p);
}
