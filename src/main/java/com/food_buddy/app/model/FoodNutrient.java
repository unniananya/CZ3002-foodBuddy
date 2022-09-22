package com.food_buddy.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="foodnutrients")
public class FoodNutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date datetime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "foodLog_id", nullable = false)
    private FoodLog foodLog;

    private double Energy_kcal;

    private double Dietary_fibre_g;

    private double Carbohydrate_g;

    private double Cholesterol_mg;

    private double Sodium_mg;

    private double Calcium_mg;

    private double Polyunsaturated_fat_g;

    private double Potassium_mg;

    private double Protein_g;

    private double Saturated_fat_g;

    private double Sugar_g;

    private double Total_fat_g;

    private double Trans_fat_mg;

    private double Vitamin_A_mcg;

    private double Vitamin_B2_mg;

    private double Vitamin_C_mg;

    private double Vitamin_D_IU;

    private double Water_g;

    private double Zinc_mg;


    public FoodNutrient() {
    }

    public FoodNutrient(Date datetime, User user, FoodLog foodLog, double energy_kcal, double dietary_fibre_g, double carbohydrate_g, double cholesterol_mg, double sodium_mg, double calcium_mg, double polyunsaturated_fat_g, double potassium_mg, double protein_g, double saturated_fat_g, double sugar_g, double total_fat_g, double trans_fat_mg, double vitamin_A_mcg, double vitamin_B2_mg, double vitamin_C_mg, double vitamin_D_IU, double water_g, double zinc_mg) {
        this.datetime = datetime;
        this.user = user;
        this.foodLog = foodLog;
        Energy_kcal = energy_kcal;
        Dietary_fibre_g = dietary_fibre_g;
        Carbohydrate_g = carbohydrate_g;
        Cholesterol_mg = cholesterol_mg;
        Sodium_mg = sodium_mg;
        Calcium_mg = calcium_mg;
        Polyunsaturated_fat_g = polyunsaturated_fat_g;
        Potassium_mg = potassium_mg;
        Protein_g = protein_g;
        Saturated_fat_g = saturated_fat_g;
        Sugar_g = sugar_g;
        Total_fat_g = total_fat_g;
        Trans_fat_mg = trans_fat_mg;
        Vitamin_A_mcg = vitamin_A_mcg;
        Vitamin_B2_mg = vitamin_B2_mg;
        Vitamin_C_mg = vitamin_C_mg;
        Vitamin_D_IU = vitamin_D_IU;
        Water_g = water_g;
        Zinc_mg = zinc_mg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FoodLog getFoodLog() {
        return foodLog;
    }

    public void setFoodLog(FoodLog foodLog) {
        this.foodLog = foodLog;
    }

    public double getEnergy_kcal() {
        return Energy_kcal;
    }

    public void setEnergy_kcal(double energy_kcal) {
        Energy_kcal = energy_kcal;
    }

    public double getDietary_fibre_g() {
        return Dietary_fibre_g;
    }

    public void setDietary_fibre_g(double dietary_fibre_g) {
        Dietary_fibre_g = dietary_fibre_g;
    }

    public double getCarbohydrate_g() {
        return Carbohydrate_g;
    }

    public void setCarbohydrate_g(double carbohydrate_g) {
        Carbohydrate_g = carbohydrate_g;
    }

    public double getCholesterol_mg() {
        return Cholesterol_mg;
    }

    public void setCholesterol_mg(double cholesterol_mg) {
        Cholesterol_mg = cholesterol_mg;
    }

    public double getSodium_mg() {
        return Sodium_mg;
    }

    public void setSodium_mg(double sodium_mg) {
        Sodium_mg = sodium_mg;
    }

    public double getCalcium_mg() {
        return Calcium_mg;
    }

    public void setCalcium_mg(double calcium_mg) {
        Calcium_mg = calcium_mg;
    }

    public double getPolyunsaturated_fat_g() {
        return Polyunsaturated_fat_g;
    }

    public void setPolyunsaturated_fat_g(double polyunsaturated_fat_g) {
        Polyunsaturated_fat_g = polyunsaturated_fat_g;
    }

    public double getPotassium_mg() {
        return Potassium_mg;
    }

    public void setPotassium_mg(double potassium_mg) {
        Potassium_mg = potassium_mg;
    }

    public double getProtein_g() {
        return Protein_g;
    }

    public void setProtein_g(double protein_g) {
        Protein_g = protein_g;
    }

    public double getSaturated_fat_g() {
        return Saturated_fat_g;
    }

    public void setSaturated_fat_g(double saturated_fat_g) {
        Saturated_fat_g = saturated_fat_g;
    }

    public double getSugar_g() {
        return Sugar_g;
    }

    public void setSugar_g(double sugar_g) {
        Sugar_g = sugar_g;
    }

    public double getTotal_fat_g() {
        return Total_fat_g;
    }

    public void setTotal_fat_g(double total_fat_g) {
        Total_fat_g = total_fat_g;
    }

    public double getTrans_fat_mg() {
        return Trans_fat_mg;
    }

    public void setTrans_fat_mg(double trans_fat_mg) {
        Trans_fat_mg = trans_fat_mg;
    }

    public double getVitamin_A_mcg() {
        return Vitamin_A_mcg;
    }

    public void setVitamin_A_mcg(double vitamin_A_mcg) {
        Vitamin_A_mcg = vitamin_A_mcg;
    }

    public double getVitamin_B2_mg() {
        return Vitamin_B2_mg;
    }

    public void setVitamin_B2_mg(double vitamin_B2_mg) {
        Vitamin_B2_mg = vitamin_B2_mg;
    }

    public double getVitamin_C_mg() {
        return Vitamin_C_mg;
    }

    public void setVitamin_C_mg(double vitamin_C_mg) {
        Vitamin_C_mg = vitamin_C_mg;
    }

    public double getVitamin_D_IU() {
        return Vitamin_D_IU;
    }

    public void setVitamin_D_IU(double vitamin_D_IU) {
        Vitamin_D_IU = vitamin_D_IU;
    }

    public double getWater_g() {
        return Water_g;
    }

    public void setWater_g(double water_g) {
        Water_g = water_g;
    }

    public double getZinc_mg() {
        return Zinc_mg;
    }

    public void setZinc_mg(double zinc_mg) {
        Zinc_mg = zinc_mg;
    }
}
