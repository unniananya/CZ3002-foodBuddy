package com.food_buddy.app.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="recommendedNutrientIntake")
public class RecommendedNutrientIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date datetime;

    private double Energy_kcal;

    private double Carbohydrate_g;

    private double Cholesterol_mg;

    private double Sodium_mg;

    private double Calcium_mg;

    private double Potassium_mg;

    private double Protein_g;

    private double Sugar_g;

    private double Vitamin_A_mcg;

    private double Vitamin_B2_mg;

    private double Vitamin_C_mg;

    private double Vitamin_D_IU;

    private double Zinc_mg;

    public RecommendedNutrientIntake() {
    }

    public RecommendedNutrientIntake(User user, Date datetime, double energy_kcal, double carbohydrate_g, double cholesterol_mg, double sodium_mg, double calcium_mg, double potassium_mg, double protein_g, double sugar_g, double vitamin_A_mcg, double vitamin_B2_mg, double vitamin_C_mg, double vitamin_D_IU, double zinc_mg) {
        this.user = user;
        this.datetime = datetime;
        Energy_kcal = energy_kcal;
        Carbohydrate_g = carbohydrate_g;
        Cholesterol_mg = cholesterol_mg;
        Sodium_mg = sodium_mg;
        Calcium_mg = calcium_mg;
        Potassium_mg = potassium_mg;
        Protein_g = protein_g;
        Sugar_g = sugar_g;
        Vitamin_A_mcg = vitamin_A_mcg;
        Vitamin_B2_mg = vitamin_B2_mg;
        Vitamin_C_mg = vitamin_C_mg;
        Vitamin_D_IU = vitamin_D_IU;
        Zinc_mg = zinc_mg;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public double getEnergy_kcal() {
        return Energy_kcal;
    }

    public void setEnergy_kcal(double energy_kcal) {
        Energy_kcal = energy_kcal;
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

    public double getSugar_g() {
        return Sugar_g;
    }

    public void setSugar_g(double sugar_g) {
        Sugar_g = sugar_g;
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

    public double getZinc_mg() {
        return Zinc_mg;
    }

    public void setZinc_mg(double zinc_mg) {
        Zinc_mg = zinc_mg;
    }
}
