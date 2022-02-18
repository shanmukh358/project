package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.Exception.IdNotFoundException;
import com.learning.entity.Food;


public interface FoodService {

	public Food addFood(Food food);

	public Food updateFood(int foodId, Food food) throws IdNotFoundException;

	public Food getFoodById(int foodId) throws IdNotFoundException;

	public String deleteFoodById(int foodId) throws IdNotFoundException;

	public Optional<List<Food>> getAllFoodDetails();

}
