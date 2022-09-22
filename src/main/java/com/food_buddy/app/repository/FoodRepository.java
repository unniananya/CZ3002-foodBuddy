package com.food_buddy.app.repository;


import com.food_buddy.app.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value="Select * from foodbuddy.food u where u.food_name = ?1", nativeQuery = true)
    public Food findbyName(String foodname);

}
