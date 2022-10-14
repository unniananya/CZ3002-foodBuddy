package com.food_buddy.app.controller;

import com.food_buddy.app.exception.ResourceNotFoundException;
import com.food_buddy.app.model.RecommendedNutrientIntake;
import com.food_buddy.app.model.User;
import com.food_buddy.app.payload.ApiResponse;
import com.food_buddy.app.payload.UserUpdateRequest;
import com.food_buddy.app.repository.RecommendedNutrientIntakeRepository;
import com.food_buddy.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    RecommendedNutrientIntakeRepository recommendedNutrientIntakeRepository;

    @GetMapping("/{userId}")
    public User viewUserProfile(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return user;
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> changeHeight(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setHeight(userUpdateRequest.getHeight());
        user.setWeight(userUpdateRequest.getWeight());
        List<String> chronicDiseases = Arrays.asList(userUpdateRequest.getChronicDiseases().split(",", -1));
        user.setChronicDiseases(chronicDiseases);
        user.setSmokingStatus(userUpdateRequest.getSmokingStatus());

        User result = userRepository.save(user);

        Date date = new Date();
        long difference_In_Time = date.getTime() - user.getDate().getTime();
        long age = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

        double calories = 0;
        if (user.getGender() == "Male"){
            calories = 13.397*userUpdateRequest.getWeight() + 4.799*userUpdateRequest.getHeight() - 5.677*age + 88.362;
        }
        else{
            calories = 9.247*userUpdateRequest.getWeight() + 3.098*userUpdateRequest.getHeight() - 4.330*age + 447.593;
        }


        double calcium=0;

        if (userUpdateRequest.getChronicDiseases().contains("Arthritis") || userUpdateRequest.getChronicDiseases().contains("Heart Disease") ){
            calcium=1500;
        }
        else if(age >= 70){
            calcium=1200;
        }
        else{
            calcium=1000;
        }

        double sodium=0;
        if (userUpdateRequest.getChronicDiseases().contains("Arthritis") || userUpdateRequest.getChronicDiseases().contains("Heart Disease") || userUpdateRequest.getChronicDiseases().contains("Diabetes") || userUpdateRequest.getChronicDiseases().contains("Respiratory Disease") ){
            sodium=1500;
        }
        else{
            sodium=2300;
        }

        double carbohydrate=0;
        if (userUpdateRequest.getChronicDiseases().contains("Heart Disease") || userUpdateRequest.getChronicDiseases().contains("Diabetes") ){
            carbohydrate=70;
        }
        else{
            carbohydrate=320;
        }

        double cholesterol=0;
        if (user.getChronicDiseases().contains("Heart Disease") ){
            cholesterol=150;
        }
        else{
            cholesterol=300;
        }


        double protein=0;
        if (user.getChronicDiseases().contains("Cancer") || user.getChronicDiseases().contains("Heart Disease") || user.getChronicDiseases().contains("Osteoporosis") || user.getChronicDiseases().contains("Respiratory Disease") ){
            protein=1.5*userUpdateRequest.getWeight();
        }
        else{
            protein=0.8*userUpdateRequest.getWeight();
        }

        double sugar=0;
        if (userUpdateRequest.getChronicDiseases().contains("Diabetes") && user.getGender() == "Male"){
            sugar=25;
        }
        else if (userUpdateRequest.getChronicDiseases().contains("Diabetes") && user.getGender() == "Female"){
            sugar=20;
        }
        else if (user.getGender() == "Male"){
            sugar=36;
        }
        else {
            sugar=24;
        }

        double vit_c=0;
        if (user.getGender() == "Male"){
            vit_c=90;
        }
        else {
            vit_c=75;
        }

        if (userUpdateRequest.getSmokingStatus()=="Yes"){
            vit_c = vit_c + 35;
        }

        double vit_d=0;
        if (userUpdateRequest.getChronicDiseases().contains("Cancer") || userUpdateRequest.getChronicDiseases().contains("Alzheimer") || userUpdateRequest.getChronicDiseases().contains("Osteoporosis")){
            vit_d=1000;
        }
        else if (age >= 70){
            vit_d=800;
        }
        else{
            vit_d=600;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);

        RecommendedNutrientIntake recommendedNutrientIntake = recommendedNutrientIntakeRepository.findLatestRec(user.getId());
        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(user,currentDate,calories,carbohydrate,cholesterol,sodium,calcium,recommendedNutrientIntake.getPotassium_mg(),protein,sugar,recommendedNutrientIntake.getVitamin_A_mcg(),recommendedNutrientIntake.getVitamin_B2_mg(),vit_c,vit_d,recommendedNutrientIntake.getZinc_mg());

        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User updated successfully"));
    }


//    @PutMapping("/{userId}/{height}")
//    public ResponseEntity<?> changeHeight(@PathVariable Long userId){
//
//    }
//    @GetMapping("/checkUsernameAvailability")
//    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
//        Boolean isAvailable = !userRepository.existsByUsername(username);
//        return new UserIdentityAvailability(isAvailable);
//    }
//
//    @GetMapping("/checkEmailAvailability")
//    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
//        Boolean isAvailable = !userRepository.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }


}
