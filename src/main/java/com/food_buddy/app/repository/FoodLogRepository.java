package com.food_buddy.app.repository;

import com.food_buddy.app.model.Food;
import com.food_buddy.app.model.FoodLog;
import com.food_buddy.app.model.FoodLogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {


    @Query(value="Select * from foodbuddy.foodlog u where u.user_id = ?1 and datediff(?2,u.datetime)=0", nativeQuery = true)
    public List<FoodLog> foodLogsToday1(Long userId, String date);

    @Query(value="Select u.id as id, f.food_name as food_name, u.no_of_servings as no_of_servings, u.type_of_meal as type_of_meal, u.datetime as datetime from foodbuddy.foodlog as u join foodbuddy.food as f on u.food_id=f.id where u.user_id = ?1 and datediff(?2,u.datetime)=0", nativeQuery = true)
    public List<FoodLogView> foodLogsToday(Long userId, String date);


//    @Query(value="Select * from foodbuddy.foodlog u where u.user_id = ?1 and timestampdiff(month,?2,u.datetime)=0")
//    public List<FoodLog> foodLogsLastMonth(Long userid, Date date);
//
//    @Query(value="Select * from foodbuddy.foodlog u where u.user_id = ?1 and timestampdiff(month,?2,u.datetime)=?3")
//    public List<FoodLog> foodLogsLastNoMonth(Long userid, Date date, Integer numberOfMths);
//
//    @Query(value="Select * from foodbuddy.foodlog u where u.user_id = ?1 and timestampdiff(year,?2,u.datetime)=0")
//    public List<FoodLog> foodLogsLastYear(Long userid, Date date);
//
//    @Query(value="Select * from foodbuddy.foodlog u where u.user_id = ?1 and timestampdiff(year,?2,u.datetime)=?3")
//    public List<FoodLog> foodLogsLastNoYear(Long userid, Date date, Integer numberOfYrs);
}
