package com.food_buddy.app.model;

import java.util.Date;

public interface FoodLogView {

    public Long getId();
    public String getFood_name();
    public Integer getNo_of_servings();
    public String getType_of_meal();
    public Date getDatetime();


}
