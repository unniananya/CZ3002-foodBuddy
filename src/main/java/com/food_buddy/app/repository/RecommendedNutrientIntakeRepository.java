package com.food_buddy.app.repository;

import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.RecommendedNutrientIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedNutrientIntakeRepository extends JpaRepository<RecommendedNutrientIntake, Long> {
}
