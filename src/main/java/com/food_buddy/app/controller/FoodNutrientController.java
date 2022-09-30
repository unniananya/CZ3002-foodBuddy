package com.food_buddy.app.controller;

import com.food_buddy.app.model.User_Report;
import com.food_buddy.app.repository.FoodNutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/report")
public class FoodNutrientController {

    @Autowired
    private FoodNutrientRepository foodNutrientRepository;

    @GetMapping("/getUserReport/{userId}")
    public List<User_Report> getUserReport(@PathVariable Long userId){
        return foodNutrientRepository.getReport(userId);
    }

    @GetMapping("/getUserReport/{userId}/{date1}/{date2}")
    public List<User_Report> getUserReport(@PathVariable Long userId, @PathVariable String date1, @PathVariable String date2){
        return foodNutrientRepository.getReportDate(userId, date1, date2);
    }
}
