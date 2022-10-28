package com.food_buddy.app.controller;


import com.food_buddy.app.model.Food;
import com.food_buddy.app.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;


    @GetMapping("/allFood")
    public List<Food> findAllFood(){
        return foodRepository.findAll();
    }

    @GetMapping("/search/{keyword}")
    public List<Food> searchFood(@PathVariable String keyword){
        return foodRepository.searchFood(keyword);
    }

    @GetMapping("/getFoodRecBeverages/{userId}")
    public List<Food> getFoodRecommendation(@PathVariable Long userId){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = formatter.format(date);
        if (foodRepository.getFoodRecommendationBeverages(userId, currentDateStr).size() == 0){
            return foodRepository.getFoodRecommendationBeverages1(userId);
        }
        else {
            return foodRepository.getFoodRecommendationBeverages(userId, currentDateStr);
        }
    }

}
