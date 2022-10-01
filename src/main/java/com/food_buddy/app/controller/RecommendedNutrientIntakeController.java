package com.food_buddy.app.controller;

import com.food_buddy.app.model.HistoricalRecommendedNutrientIntake;
import com.food_buddy.app.repository.RecommendedNutrientIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/nutrient_intake_recommendation")

public class RecommendedNutrientIntakeController {
    @Autowired
    private RecommendedNutrientIntakeRepository recommendedNutrientIntakeRepository;

    @GetMapping("/getHistory/{userId}/{startDate}/{endDate}")
    public List<HistoricalRecommendedNutrientIntake> getRecommendedNutrientIntakeInDateRange(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate){
        return recommendedNutrientIntakeRepository.getRecommendedNutrientIntakeInDateRange(userId, startDate, endDate);
    }
}
