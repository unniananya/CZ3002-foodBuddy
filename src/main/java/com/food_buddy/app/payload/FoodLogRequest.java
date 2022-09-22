package com.food_buddy.app.payload;

import com.food_buddy.app.model.Food;
import com.food_buddy.app.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class FoodLogRequest {

    @NotBlank
    private String foodname;

    @NotNull
    private Integer no_of_servings;

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public Integer getNo_of_servings() {
        return no_of_servings;
    }

    public void setNo_of_servings(Integer no_of_servings) {
        this.no_of_servings = no_of_servings;
    }
}
