package com.food_buddy.app.controller;

import com.food_buddy.app.exception.ResourceNotFoundException;
import com.food_buddy.app.model.Food;
import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.FoodNutrient;
import com.food_buddy.app.model.User;
import com.food_buddy.app.payload.ApiResponse;
import com.food_buddy.app.payload.FoodLogRequest;
import com.food_buddy.app.repository.FoodLogRepository;
import com.food_buddy.app.repository.FoodNutrientRepository;
import com.food_buddy.app.repository.FoodRepository;
import com.food_buddy.app.repository.UserRepository;
import com.food_buddy.app.security.CurrentUser;
import com.food_buddy.app.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/foodLog")
public class FoodLogController {

    @Autowired
    private FoodLogRepository foodLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodNutrientRepository foodNutrientRepository;


    @PostMapping("/{userId}/addFoodLog")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addFoodLog(@PathVariable Long userId, @Valid @RequestBody FoodLogRequest foodLogRequest) throws ParseException {
//        @CurrentUser UserPrincipal currentUser

//        User user = user  Repository.getOne(currentUser.getId());
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));;

//        Food food = foodRepository.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("Food", "id", foodId));
//
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);

        Food food = foodRepository.findbyName(foodLogRequest.getFoodname());

        FoodLog foodLog = new FoodLog(currentDate,food,user,foodLogRequest.getNo_of_servings());
//        foodLog.setDatetime(currentDate);
//        foodLog.setFood(food);
//        foodLog.setUser(user);

        FoodLog result = foodLogRepository.save(foodLog);

        FoodNutrient foodNutrient = new FoodNutrient(currentDate,user,foodLog,food.getEnergy_kcal(),food.getDietary_fibre_g(),food.getCarbohydrate_g(),food.getCholesterol_mg(),food.getSodium_mg(),food.getCalcium_mg(),food.getPolyunsaturated_fat_g(),food.getPotassium_mg(),food.getProtein_g(),food.getSaturated_fat_g(),food.getSugar_g(),food.getTotal_fat_g(),food.getTrans_fat_mg(),food.getVitamin_A_mcg(),food.getVitamin_B2_mg(),food.getVitamin_C_mg(),food.getVitamin_D_IU(),food.getWater_g(),food.getZinc_mg());

        FoodNutrient result1 = foodNutrientRepository.save(foodNutrient);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{foodLogId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "FoodLog saved successfully"));
    }

    @GetMapping("/{userId}/todayFoodLogs")
    public List<FoodLog> foodLogsToday(@PathVariable Long userId) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsToday(userId,currentDate);
    }

    @GetMapping("/{userId}/monthsFoodLogs")
    public List<FoodLog> foodLogsLastMonth(@PathVariable Long userId) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsLastMonth(userId,currentDate);
    }

    @GetMapping("/{userId}/{numberOfMths}/monthsFoodLogs")
    public List<FoodLog> foodLogsLastNoMonth(@PathVariable Long userId,@PathVariable Integer numberOfMths) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsLastNoMonth(userId,currentDate,numberOfMths);
    }


    @GetMapping("/{userId}/yearsFoodLogs")
    public List<FoodLog> foodLogsLastYear(@PathVariable Long userId) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsLastYear(userId,currentDate);
    }

    @GetMapping("/{userId}/{numberOfYrs}/yearsFoodLogs")
    public List<FoodLog> foodLogsLastNoYear(@PathVariable Long userId,@PathVariable Integer numberOfYrs) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsLastNoYear(userId,currentDate,numberOfYrs);
    }




}
