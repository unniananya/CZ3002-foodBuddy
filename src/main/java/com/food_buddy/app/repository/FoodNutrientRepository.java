package com.food_buddy.app.repository;

import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.FoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodNutrientRepository extends JpaRepository<FoodNutrient, Long> {



}
