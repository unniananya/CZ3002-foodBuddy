package com.food_buddy.app.repository;


import com.food_buddy.app.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value="Select * from foodbuddy.food u where binary u.food_name = ?1", nativeQuery = true)
    public Food findbyName(String foodname);

    @Query(value="Select * from foodbuddy.food u where u.food_name like %?1%", nativeQuery = true)
    public List<Food> searchFood(String keyword);

    @Query(value="select f.* from food as f,(select date(f.datetime) as date, re.calcium_mg-sum(f.calcium_mg) as daily_calcium, re.carbohydrate_g-sum(f.carbohydrate_g) as daily_carbohydrate, re.cholesterol_mg-sum(f.cholesterol_mg) as daily_cholesterol, re.energy_kcal-sum(f.energy_kcal) as daily_energy, re.potassium_mg-sum(f.potassium_mg) as daily_potassium, re.protein_g-sum(f.protein_g) as daily_protein, re.sodium_mg-sum(f.sodium_mg) as daily_sodium, re.sugar_g-sum(f.sugar_g) as daily_sugar, re.vitamin_a_mcg-sum(f.vitamin_a_mcg) as daily_vit_a, re.vitamin_b2_mg-sum(f.vitamin_b2_mg) as daily_vit_b2, re.vitamin_c_mg-sum(f.vitamin_c_mg) as daily_vit_c, re.vitamin_d_iu-sum(f.vitamin_d_iu) as daily_vit_d, re.zinc_mg-sum(f.zinc_mg) as daily_zinc from foodbuddy.foodnutrients as f join foodbuddy.recommended_nutrient_intake as re on re.datetime = (select re1.datetime from foodbuddy.recommended_nutrient_intake as re1 where date(f.datetime) >= date(re1.datetime) and f.user_id = re1.user_id order by re1.datetime desc limit 1) where f.user_id= ?1 group by day(f.datetime)) as a where f.Energy_kcal <= a.daily_energy and f.Carbohydrate_g<=a.daily_carbohydrate and f.Cholesterol_mg<=a.daily_cholesterol and f.Sodium_mg<=a.daily_sodium and f.Calcium_mg<=a.daily_calcium and f.Potassium_mg<=a.daily_potassium and f.Protein_g<=a.daily_protein and f.Sugar_g<=a.daily_sugar and f.Vitamin_A_mcg<=a.daily_vit_a and f.Vitamin_B2_mg<=a.daily_vit_b2 and f.Vitamin_C_mg<=a.daily_vit_c and f.Vitamin_D_IU<=a.daily_vit_d and f.Zinc_mg <= a.daily_zinc and a.date=?2 and f.food_group='BEVERAGES' limit 3" , nativeQuery = true)
    public List<Food> getFoodRecommendationBeverages(Long userId, String date);

    @Query(value="select f.* from food as f, (Select * from foodbuddy.recommended_nutrient_intake u where u.user_id = 1 order by u.datetime desc limit 1) as re where f.Energy_kcal <= re.energy_kcal and f.Carbohydrate_g<=re.carbohydrate_g and f.Cholesterol_mg<=re.cholesterol_mg and f.Sodium_mg<=re.sodium_mg and f.Calcium_mg<=re.calcium_mg and f.Potassium_mg<=re.potassium_mg and f.Protein_g<=re.protein_g and f.Sugar_g<=re.sugar_g and f.Vitamin_A_mcg<=re.vitamin_a_mcg and f.Vitamin_B2_mg<=re.vitamin_b2_mg and f.Vitamin_C_mg<=re.vitamin_c_mg and f.Vitamin_D_IU<=re.vitamin_d_iu and f.Zinc_mg <= re.zinc_mg and f.food_group='BEVERAGES' limit 3", nativeQuery = true)
    public List<Food> getFoodRecommendationBeverages1(Long userId);
    

    
}
