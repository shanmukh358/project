package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Exception.IdNotFoundException;
import com.learning.entity.Food;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceimpl implements FoodService {
	
	@Autowired
	FoodRepository foodRepository;

	@Override
	public Food addFood(Food food) {
		// TODO Auto-generated method stub
		Food food2 = foodRepository.save(food);
		if (food2 != null) {
			return food2;
		} else {
			return null;
		}
	}

	@Override
	public Food updateFood(int foodId, Food food) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(!this.foodRepository.existsById(foodId))
			throw new IdNotFoundException("invalid id");
		
		return foodRepository.save(food);
	}

	@Override
	public Food getFoodById(int foodId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> optional =  foodRepository.findById(foodId);
		if(optional.isEmpty()) {
			throw new IdNotFoundException("id does not exists");
		}
		else {
			return optional.get();
		}
	}

	@Override
	public String deleteFoodById(int foodId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Food optional;
		try {
			optional = this.getFoodById(foodId);
			if(optional==null) {
				throw new IdNotFoundException("record not found");
			}
			else {
				foodRepository.deleteById(foodId);
				return "register record deleted";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Optional<List<Food>> getAllFoodDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepository.findAll());
	}

}
