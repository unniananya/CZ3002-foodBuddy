package com.food_buddy.app.repository;

import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.HistoricalRecommendedNutrientIntake;
import com.food_buddy.app.model.RecommendedNutrientIntake;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecommendedNutrientIntakeRepository extends JpaRepository<RecommendedNutrientIntake, Long> {

    @Query(value="Select * from foodbuddy.recommended_nutrient_intake u where u.user_id = ?1 order by u.datetime desc limit 1", nativeQuery = true)
    public RecommendedNutrientIntake findLatestRec(Long userId);

    @Query(value="SELECT t1.calcium_mg as calciumAmount, t1.carbohydrate_g as carbohydrateAmount, t1.energy_kcal as calorieAmount, t1.potassium_mg as potassiumAmount, t1.protein_g as proteinAmount, t1.sodium_mg as sodiumAmount, t1.sugar_g as sugarAmount, t1.vitamin_a_mcg as vitaminAAmount, t1.vitamin_b2_mg as vitaminB2Amount, t1.vitamin_c_mg as vitaminCAmount, t1.vitamin_d_iu as vitaminDAmount, t1.zinc_mg as zincAmount, DATE(t1.datetime) as date, t1.user_id FROM foodbuddy.recommended_nutrient_intake t1 where t1.user_id=?1 order by t1.datetime desc limit 1", nativeQuery = true)
    public HistoricalRecommendedNutrientIntake findLatestRec1(Long userId);

    @Query(value="SELECT t1.calcium_mg as calciumAmount, t1.carbohydrate_g as carbohydrateAmount, t1.energy_kcal as calorieAmount, t1.potassium_mg as potassiumAmount, t1.protein_g as proteinAmount, t1.sodium_mg as sodiumAmount, t1.sugar_g as sugarAmount, t1.vitamin_a_mcg as vitaminAAmount, t1.vitamin_b2_mg as vitaminB2Amount, t1.vitamin_c_mg as vitaminCAmount, t1.vitamin_d_iu as vitaminDAmount, t1.zinc_mg as zincAmount, DATE(t1.datetime) as date, t1.user_id FROM foodbuddy.recommended_nutrient_intake t1 JOIN (SELECT user_id, MAX(STR_TO_DATE(datetime,'%Y-%m-%d %H:%i:%s')) max_date FROM recommended_nutrient_intake GROUP BY user_id, DATE(datetime)) t2 ON t1.datetime = t2.max_date AND t1.user_id = t2.user_id WHERE t1.user_id = ?1 AND DATE(t1.datetime) between ?2 and ?3", nativeQuery = true)
    public List<HistoricalRecommendedNutrientIntake> getRecommendedNutrientIntakeInDateRange(Long userId, String startDate, String endDate);

//    @Modifying
//    @Transactional
//    @Query(value="set @i = -1;select x.calciumAmount, x.carbohydrateAmount, x.calorieAmount, x.potassiumAmount, x.proteinAmount, x.sodiumAmount, x.sugarAmount, x.vitaminAAmount, x.vitaminB2Amount, x.vitaminCAmount, x.vitaminDAmount, x.zincAmount, x.date from (select a.calciumAmount, a.carbohydrateAmount, a.calorieAmount, a.potassiumAmount, a.proteinAmount, a.sodiumAmount, a.sugarAmount, a.vitaminAAmount, a.vitaminB2Amount, a.vitaminCAmount, a.vitaminDAmount, a.zincAmount, b.date, a.date as recdate, ROW_NUMBER() OVER(PARTITION BY b.date order by a.date desc) AS row_num from (SELECT t1.calcium_mg as calciumAmount, t1.carbohydrate_g as carbohydrateAmount, t1.energy_kcal as calorieAmount, t1.potassium_mg as potassiumAmount, t1.protein_g as proteinAmount, t1.sodium_mg as sodiumAmount, t1.sugar_g as sugarAmount, t1.vitamin_a_mcg as vitaminAAmount, t1.vitamin_b2_mg as vitaminB2Amount, t1.vitamin_c_mg as vitaminCAmount, t1.vitamin_d_iu as vitaminDAmount, t1.zinc_mg as zincAmount, DATE(t1.datetime) as date, t1.user_id FROM foodbuddy.recommended_nutrient_intake t1 JOIN (SELECT user_id, MAX(STR_TO_DATE(datetime,'%Y-%m-%d %H:%i:%s')) max_date FROM recommended_nutrient_intake GROUP BY user_id, DATE(datetime)) t2 ON t1.datetime = t2.max_date AND t1.user_id = t2.user_id WHERE t1.user_id = ?1 AND DATE(t1.datetime) between ?2 and ?3) as a,(SELECT DATE(ADDDATE('2022-09-24', INTERVAL @i\\:=@i+1 DAY)) AS date FROM food HAVING @i < DATEDIFF(?3, ?2)) as b where a.date<=b.date order by b.date) as x where x.row_num=1",nativeQuery = true)
//    public List<HistoricalRecommendedNutrientIntake> getRecommendedNutrientIntakeInDateRange(Long userId, String startDate, String endDate);

}
