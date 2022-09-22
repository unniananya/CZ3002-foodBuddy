package com.food_buddy.app.controller;


import com.food_buddy.app.model.Food;
import com.food_buddy.app.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
