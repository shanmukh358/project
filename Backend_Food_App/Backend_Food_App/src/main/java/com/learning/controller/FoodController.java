package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import com.learning.entity.Food;
import com.learning.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
// @CrossOrigin // handled at gateway (application.yml)
public class FoodController {

    @Autowired
    private FoodService service;

    @GetMapping
    public Page<Food> getAllFoodByPage(@RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return service.getAllFoodByPage(page - 1, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable Integer id) {
        Food food = service.getFoodById(id);
        if (food == null) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Sorry, food not found with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        return ResponseEntity.ok(food);
    }

    @GetMapping("/filter")
    public Page<Food> getAllFoodByFoodType(@RequestParam String foodType,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return service.getAllFoodByFoodType(foodType, page - 1, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodById(@PathVariable Integer id) {
        Food food = service.getFoodById(id);
        if (food == null) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Sorry, food not found with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        } else {
            service.deleteFoodById(id);
            return ResponseEntity.ok(food);
        }
    }

    @PostMapping
    public ResponseEntity<?> postFood(@RequestBody Food food) {
        Food savedFood = service.addNewFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Integer id, @RequestBody Food food) {
        food.setId(id);
        Food updatedFood = service.updateFood(food);
        return ResponseEntity.ok(updatedFood);
    }
}
