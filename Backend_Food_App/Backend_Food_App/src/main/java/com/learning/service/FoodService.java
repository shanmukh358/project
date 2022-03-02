package com.learning.service;

import java.util.Optional;

import com.learning.entity.Food;
import com.learning.repo.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository repo;

    public Food getFoodById(Integer id) {
        Optional<Food> food = repo.findById(id);
        if (food.isPresent()) {
            return food.get();
        }
        return null;
    }

    public Page<Food> getAllFoodByPage(int page, int size) {
        PageRequest p = PageRequest.of(page, size);
        return repo.findAll(p);
    }

    public Page<Food> getAllFoodByFoodType(String foodType, int page, int size) {
        PageRequest p = PageRequest.of(page, size);
        return repo.findAllByFoodType(foodType, p);
    }

    public void deleteFoodById(Integer id) {
        repo.deleteById(id);
    }

    public Food addNewFood(Food food) {
        food.setId(null);
        return repo.save(food);
    }

    public Food updateFood(Food food) {
        return repo.save(food);
    }
}
