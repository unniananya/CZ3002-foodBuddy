package com.food_buddy.app.payload;

import com.food_buddy.app.StringToListConverter;

import javax.persistence.Convert;
import java.util.List;

public class UserUpdateRequest {

    private float height;

    private float weight;

    private List<String> chronicDiseases;

    @Convert(converter = StringToListConverter.class)
    private String smokingStatus;


    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<String> getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(List<String> chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) {
        this.smokingStatus = smokingStatus;
    }
}
