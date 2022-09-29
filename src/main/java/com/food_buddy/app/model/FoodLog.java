package com.food_buddy.app.model;

import com.food_buddy.app.model.audit.DateAudit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="foodlog")
public class FoodLog extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date datetime;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Integer no_of_servings;

    @NotBlank
    private String type_of_meal;

    public FoodLog() {
    }


    public FoodLog(Date datetime, Food food, User user, Integer no_of_servings, String type_of_meal) {
        this.datetime = datetime;
        this.food = food;
        this.user = user;
        this.no_of_servings = no_of_servings;
        this.type_of_meal = type_of_meal;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNo_of_servings() {
        return no_of_servings;
    }

    public void setNo_of_servings(Integer no_of_servings) {
        this.no_of_servings = no_of_servings;
    }

    public String getType_of_meal() {
        return type_of_meal;
    }

    public void setType_of_meal(String type_of_meal) {
        this.type_of_meal = type_of_meal;
    }
}
