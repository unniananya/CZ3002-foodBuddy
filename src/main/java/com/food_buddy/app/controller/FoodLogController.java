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

//        User user = userRepository.getOne(currentUser.getId());
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

//        Food food = foodRepository.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("Food", "id", foodId));
//
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);

        Food food = foodRepository.findbyName(foodLogRequest.getFoodname());

        FoodLog foodLog = new FoodLog(currentDate,food,user,foodLogRequest.getNo_of_servings(), foodLogRequest.getType_of_meal());
//        foodLog.setDatetime(currentDate);
//        foodLog.setFood(food);
//        foodLog.setUser(user);

        FoodLog result = foodLogRepository.save(foodLog);

        FoodNutrient foodNutrient = new FoodNutrient(currentDate,user,foodLog,food.getEnergy_kcal()*foodLog.getNo_of_servings(),food.getDietary_fibre_g()*foodLog.getNo_of_servings(),food.getCarbohydrate_g()*foodLog.getNo_of_servings(),food.getCholesterol_mg()*foodLog.getNo_of_servings(),food.getSodium_mg()*foodLog.getNo_of_servings(),food.getCalcium_mg()*foodLog.getNo_of_servings(),food.getPolyunsaturated_fat_g()*foodLog.getNo_of_servings(),food.getPotassium_mg()*foodLog.getNo_of_servings(),food.getProtein_g()*foodLog.getNo_of_servings(),food.getSaturated_fat_g()*foodLog.getNo_of_servings(),food.getSugar_g()*foodLog.getNo_of_servings(),food.getTotal_fat_g()*foodLog.getNo_of_servings(),food.getTrans_fat_mg()*foodLog.getNo_of_servings(),food.getVitamin_A_mcg()*foodLog.getNo_of_servings(),food.getVitamin_B2_mg()*foodLog.getNo_of_servings(),food.getVitamin_C_mg()*foodLog.getNo_of_servings(),food.getVitamin_D_IU()*foodLog.getNo_of_servings(),food.getWater_g()*foodLog.getNo_of_servings(),food.getZinc_mg()*foodLog.getNo_of_servings());

        FoodNutrient result1 = foodNutrientRepository.save(foodNutrient);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{foodLogId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "FoodLog saved successfully"));
    }

    //delete food log
    //edit food log



    @GetMapping("/{userId}/todayFoodLogs")
    public List<FoodLog> foodLogsToday(@PathVariable Long userId) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);
        return foodLogRepository.foodLogsToday(userId,currentDateStr);
    }

    @PutMapping("/{userId}/{foodLogId}/update")
    public ResponseEntity<?> updateFoodLog(@PathVariable Long userId, @PathVariable Long foodLogId, @Valid @RequestBody FoodLogRequest foodLogRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        FoodLog foodLog = foodLogRepository.findById(foodLogId).orElseThrow(() -> new ResourceNotFoundException("FoodLog", "id", foodLogId));
        Food food = foodRepository.findbyName(foodLogRequest.getFoodname());
        Integer no_of_servings = foodLogRequest.getNo_of_servings();

        foodLog.setFood(food);
        foodLog.setNo_of_servings(no_of_servings);

        FoodLog result = foodLogRepository.save(foodLog);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{foodLogId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "FoodLog updated successfully"));

    }

//    @GetMapping("/{userId}/monthsFoodLogs")
//    public List<FoodLog> foodLogsLastMonth(@PathVariable Long userId) throws ParseException {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//        return foodLogRepository.foodLogsLastMonth(userId,currentDate);
//    }
//
//    @GetMapping("/{userId}/{numberOfMths}/monthsFoodLogs")
//    public List<FoodLog> foodLogsLastNoMonth(@PathVariable Long userId,@PathVariable Integer numberOfMths) throws ParseException {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//        return foodLogRepository.foodLogsLastNoMonth(userId,currentDate,numberOfMths);
//    }
//
//
//    @GetMapping("/{userId}/yearsFoodLogs")
//    public List<FoodLog> foodLogsLastYear(@PathVariable Long userId) throws ParseException {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//        return foodLogRepository.foodLogsLastYear(userId,currentDate);
//    }
//
//    @GetMapping("/{userId}/{numberOfYrs}/yearsFoodLogs")
//    public List<FoodLog> foodLogsLastNoYear(@PathVariable Long userId,@PathVariable Integer numberOfYrs) throws ParseException {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//        return foodLogRepository.foodLogsLastNoYear(userId,currentDate,numberOfYrs);
//    }




}
