package com.learning.service;

import java.util.Optional;

import com.learning.Exception.IdNotFoundException;
import com.learning.entity.FoodType;

public interface FoodTypeService {
	public String addFoodType(FoodType foodType);
	public void deleteFoodType(int foodTypeId) throws IdNotFoundException;
	public Optional<FoodType> getFoodTypeById(int foodTypeId);
}
