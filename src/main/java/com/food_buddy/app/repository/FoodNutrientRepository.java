package com.food_buddy.app.repository;

import com.food_buddy.app.model.FoodNutrient;
import com.food_buddy.app.model.HistoricalNutrientData;
import com.food_buddy.app.model.TodaysNutrientIntake;
import com.food_buddy.app.model.User_Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodNutrientRepository extends JpaRepository<FoodNutrient, Long> {

    @Query(value="select a.date as date, case when a.daily_calcium <= 1.1*a.calcium_mg and a.daily_calcium >= 0.9*a.calcium_mg then 1 else 0 end as calcium, case when a.daily_carbohydrate <= 1.1*a.carbohydrate_g and a.daily_carbohydrate >= 0.9*a.carbohydrate_g then 1 else 0 end as carbohydrate, case when a.daily_cholesterol <= 1.1*a.cholesterol_mg and a.daily_cholesterol >= 0.9*a.cholesterol_mg then 1 else 0 end as cholesterol, case when a.daily_energy <= 1.1*a.energy_kcal and a.daily_energy >= 0.9*a.energy_kcal then 1 else 0 end as energy, case when a.daily_potassium <= 1.1*a.potassium_mg and a.daily_potassium >= 0.9*a.potassium_mg then 1 else 0 end as potassium, case when a.daily_protein <= 1.1*a.protein_g and a.daily_protein >= 0.9*a.protein_g then 1 else 0 end as protein, case when a.daily_sodium <= 1.1*a.sodium_mg and a.daily_sodium >= 0.9*a.sodium_mg then 1 else 0 end as sodium, case when a.daily_sugar <= 1.1*a.sugar_g and a.daily_sugar >= 0.9*a.sugar_g then 1 else 0 end as sugar, case when a.daily_vit_a <= 1.1*a.vitamin_a_mcg and a.daily_vit_a >= 0.9*a.vitamin_a_mcg then 1 else 0 end as vitamin_a, case when a.daily_vit_b2 <= 1.1*a.vitamin_b2_mg and a.daily_vit_b2 >= 0.9*a.vitamin_b2_mg then 1 else 0 end as vitamin_b2, case when a.daily_vit_c <= 1.1*a.vitamin_c_mg and a.daily_vit_c >= 0.9*a.vitamin_c_mg then 1 else 0 end as vitamin_c, case when a.daily_vit_d <= 1.1*a.vitamin_d_iu and a.daily_vit_d >= 0.9*a.vitamin_d_iu then 1 else 0 end as vitamin_d, case when a.daily_zinc <= 1.1*a.zinc_mg and a.daily_zinc >= 0.9*a.zinc_mg then 1 else 0 end as zinc from (select date(f.datetime) as date, sum(f.calcium_mg) as daily_calcium, sum(f.carbohydrate_g) as daily_carbohydrate, sum(f.cholesterol_mg) as daily_cholesterol, sum(f.energy_kcal) as daily_energy, sum(f.potassium_mg) as daily_potassium, sum(f.protein_g) as daily_protein, sum(f.sodium_mg) as daily_sodium, sum(f.sugar_g) as daily_sugar, sum(f.vitamin_a_mcg) as daily_vit_a, sum(f.vitamin_b2_mg) as daily_vit_b2, sum(f.vitamin_c_mg) as daily_vit_c, sum(f.vitamin_d_iu) as daily_vit_d, sum(f.zinc_mg) as daily_zinc, re.* from foodbuddy.foodnutrients as f join foodbuddy.recommended_nutrient_intake as re on re.datetime = (select re1.datetime from foodbuddy.recommended_nutrient_intake as re1 where date(f.datetime) >= date(re1.datetime) and f.user_id = re1.user_id order by re1.datetime desc limit 1) where f.user_id= ?1 group by date(f.datetime)) as a", nativeQuery = true)
    public List<User_Report> getReport(Long userId);

    @Query(value="select a.date as date, case when a.daily_calcium <= 1.1*a.calcium_mg and a.daily_calcium >= 0.9*a.calcium_mg then 1 else 0 end as calcium, case when a.daily_carbohydrate <= 1.1*a.carbohydrate_g and a.daily_carbohydrate >= 0.9*a.carbohydrate_g then 1 else 0 end as carbohydrate, case when a.daily_cholesterol <= 1.1*a.cholesterol_mg and a.daily_cholesterol >= 0.9*a.cholesterol_mg then 1 else 0 end as cholesterol, case when a.daily_energy <= 1.1*a.energy_kcal and a.daily_energy >= 0.9*a.energy_kcal then 1 else 0 end as energy, case when a.daily_potassium <= 1.1*a.potassium_mg and a.daily_potassium >= 0.9*a.potassium_mg then 1 else 0 end as potassium, case when a.daily_protein <= 1.1*a.protein_g and a.daily_protein >= 0.9*a.protein_g then 1 else 0 end as protein, case when a.daily_sodium <= 1.1*a.sodium_mg and a.daily_sodium >= 0.9*a.sodium_mg then 1 else 0 end as sodium, case when a.daily_sugar <= 1.1*a.sugar_g and a.daily_sugar >= 0.9*a.sugar_g then 1 else 0 end as sugar, case when a.daily_vit_a <= 1.1*a.vitamin_a_mcg and a.daily_vit_a >= 0.9*a.vitamin_a_mcg then 1 else 0 end as vitamin_a, case when a.daily_vit_b2 <= 1.1*a.vitamin_b2_mg and a.daily_vit_b2 >= 0.9*a.vitamin_b2_mg then 1 else 0 end as vitamin_b2, case when a.daily_vit_c <= 1.1*a.vitamin_c_mg and a.daily_vit_c >= 0.9*a.vitamin_c_mg then 1 else 0 end as vitamin_c, case when a.daily_vit_d <= 1.1*a.vitamin_d_iu and a.daily_vit_d >= 0.9*a.vitamin_d_iu then 1 else 0 end as vitamin_d, case when a.daily_zinc <= 1.1*a.zinc_mg and a.daily_zinc >= 0.9*a.zinc_mg then 1 else 0 end as zinc from (select date(f.datetime) as date, sum(f.calcium_mg) as daily_calcium, sum(f.carbohydrate_g) as daily_carbohydrate, sum(f.cholesterol_mg) as daily_cholesterol, sum(f.energy_kcal) as daily_energy, sum(f.potassium_mg) as daily_potassium, sum(f.protein_g) as daily_protein, sum(f.sodium_mg) as daily_sodium, sum(f.sugar_g) as daily_sugar, sum(f.vitamin_a_mcg) as daily_vit_a, sum(f.vitamin_b2_mg) as daily_vit_b2, sum(f.vitamin_c_mg) as daily_vit_c, sum(f.vitamin_d_iu) as daily_vit_d, sum(f.zinc_mg) as daily_zinc, re.* from foodbuddy.foodnutrients as f join foodbuddy.recommended_nutrient_intake as re on re.datetime = (select re1.datetime from foodbuddy.recommended_nutrient_intake as re1 where date(f.datetime) >= date(re1.datetime) and f.user_id = re1.user_id order by re1.datetime desc limit 1) where f.user_id= ?1 group by date(f.datetime)) as a where a.date >= ?2 and a.date <= ?3", nativeQuery = true)
    public List<User_Report> getReportDate(Long userId, String date1, String date2);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.energy_kcal) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getCaloriesInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.calcium_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getCalciumInDateRange (Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.carbohydrate_g) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getCarbohydratesInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.potassium_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getPotassiumInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.protein_g) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getProteinInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.sodium_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getSodiumInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.sugar_g) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getSugarInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.vitamin_a_mcg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getVitaminAInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.vitamin_b2_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getVitaminB2InDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.vitamin_c_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getVitaminCInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.vitamin_d_iu) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getVitaminDInDateRange(Long userId, String startDate, String endDate);

    @Query(value = "SELECT DATE(f.datetime) as date, SUM(f.zinc_mg) as nutrientAmount FROM FoodNutrients f WHERE DATE(f.datetime) between ?2 and ?3 AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public List<HistoricalNutrientData> getZincInDateRange(Long userId, String startDate, String endDate);
    @Query(value = "SELECT SUM(f.energy_kcal) as calorieAmount, SUM(f.calcium_mg) as calciumAmount, SUM(f.carbohydrate_g) as carbohydrateAmount, SUM(f.potassium_mg) as potassiumAmount, SUM(f.protein_g) as proteinAmount, SUM(f.sodium_mg) as sodiumAmount, SUM(f.sugar_g) as sugarAmount, SUM(f.vitamin_a_mcg) as vitaminAAmount, SUM(f.vitamin_b2_mg) as vitaminBAmount, SUM(f.vitamin_c_mg) as vitaminCAmount, SUM(f.vitamin_d_iu) as vitaminDAmount, SUM(f.zinc_mg) as zincAmount FROM FoodNutrients f WHERE DATE(f.datetime) = CAST(NOW() AS date) AND f.user_id = ?1 GROUP BY DATE(f.datetime)", nativeQuery = true)
    public TodaysNutrientIntake getTodaysNutrientIntake(Long userId);
}
