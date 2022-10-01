package com.food_buddy.app.controller;

import com.food_buddy.app.model.HistoricalNutrientData;
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

    // ensure that end date is not in the future
    // startDate and endDate strings need to be in the right format
    @GetMapping("/getHistoricalCalorieData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalCalorieData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getCaloriesInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalCalciumData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getCalcium(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getCalciumInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalCarbohydratesData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalCarbohydratesData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getCarbohydratesInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalPotassiumData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalPotassiumData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getPotassiumInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalProteinData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalProteinData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getProteinInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalSodiumData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalSodiumData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getSodiumInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalSugarData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalSugarData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getSugarInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalVitaminAData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalVitaminAData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getVitaminAInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalVitaminB2Data/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalVitaminB2Data(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getVitaminB2InDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalVitaminCData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalVitaminCData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getVitaminCInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalVitaminDData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalVitaminDData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getVitaminDInDateRange(userId, startDate, endDate);
    }

    @GetMapping("/getHistoricalZincData/{userId}/{startDate}/{endDate}")
    public List<HistoricalNutrientData> getHistoricalZincData(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        return foodNutrientRepository.getZincInDateRange(userId, startDate, endDate);
    }

}
