package com.food_buddy.app.repository;

import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.RecommendedNutrientIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedNutrientIntakeRepository extends JpaRepository<RecommendedNutrientIntake, Long> {

    @Query(value="Select * from foodbuddy.recommended_nutrient_intake u where u.user_id = ?1 order by u.datetime desc limit 1", nativeQuery = true)
    public RecommendedNutrientIntake findLatestRec(Long userId);
}
