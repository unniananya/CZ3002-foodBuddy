package com.food_buddy.app.controller;


import com.food_buddy.app.exception.AppException;
import com.food_buddy.app.model.RecommendedNutrientIntake;
import com.food_buddy.app.model.Role;
import com.food_buddy.app.model.RoleName;
import com.food_buddy.app.model.User;
import com.food_buddy.app.payload.ApiResponse;
import com.food_buddy.app.payload.JwtAuthenticationResponse;
import com.food_buddy.app.payload.LoginRequest;
import com.food_buddy.app.payload.SignUpRequest;
import com.food_buddy.app.repository.RecommendedNutrientIntakeRepository;
import com.food_buddy.app.repository.RoleRepository;
import com.food_buddy.app.repository.UserRepository;
import com.food_buddy.app.security.JwtTokenProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @Autowired
    RecommendedNutrientIntakeRepository recommendedNutrientIntakeRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws ParseException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        String chronicDiseaseStr = signUpRequest.getChronicDiseases();
        List<String> chronicDiseases = Arrays.asList(chronicDiseaseStr.split(",", -1));
        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getHeight(), signUpRequest.getWeight(), signUpRequest.getDate(), signUpRequest.getGender(), chronicDiseases, signUpRequest.getSmokingStatus());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);


        String gender = user.getGender();

        Date date = new Date();
        long difference_In_Time = date.getTime() - user.getDate().getTime();
        long age = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));



        double calories = 0;
        if (gender == "Male"){
            calories = 13.397*user.getWeight() + 4.799*user.getHeight() - 5.677*age + 88.362;
        }
        else{
            calories = 9.247*user.getWeight() + 3.098*user.getHeight() - 4.330*age + 447.593;
        }


        double calcium=0;

        if (user.getChronicDiseases().contains("Arthritis") || user.getChronicDiseases().contains("Heart Disease") ){
            calcium=1500;
        }
        else if(age >= 70){
            calcium=1200;
        }
        else{
            calcium=1000;
        }

        double sodium=0;
        if (user.getChronicDiseases().contains("Arthritis") || user.getChronicDiseases().contains("Heart Disease") || user.getChronicDiseases().contains("Diabetes") || user.getChronicDiseases().contains("Respiratory Disease") ){
            sodium=1500;
        }
        else{
            sodium=2300;
        }

        double carbohydrate=0;
        if (user.getChronicDiseases().contains("Heart Disease") || user.getChronicDiseases().contains("Diabetes") ){
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

        double potassium = 4700;

        float weight = user.getWeight();

        double protein=0;
        if (user.getChronicDiseases().contains("Cancer") || user.getChronicDiseases().contains("Heart Disease") || user.getChronicDiseases().contains("Osteoporosis") || user.getChronicDiseases().contains("Respiratory Disease") ){
            protein=1.5*weight;
        }
        else{
            protein=0.8*weight;
        }

        double sugar=0;
        if (user.getChronicDiseases().contains("Diabetes") && gender == "Male"){
            sugar=25;
        }
        else if (user.getChronicDiseases().contains("Diabetes") && gender == "Female"){
            sugar=20;
        }
        else if (gender == "Male"){
            sugar=36;
        }
        else {
            sugar=24;
        }

        double vit_a=0;
        if (gender == "Male"){
            vit_a=900;
        }
        else {
            vit_a=700;
        }

        double vit_b2=1.2;


        double vit_c=0;
        if (gender == "Male"){
            vit_c=90;
        }
        else {
            vit_c=75;
        }

        if (user.getSmokingStatus()=="Yes"){
            vit_c = vit_c + 35;
        }

        double vit_d=0;
        if (user.getChronicDiseases().contains("Cancer") || user.getChronicDiseases().contains("Alzheimer") || user.getChronicDiseases().contains("Osteoporosis")){
            vit_d=1000;
        }
        else if (age >= 70){
            vit_d=800;
        }
        else{
            vit_d=600;
        }


        double zinc=0;
        if (gender == "Male"){
            zinc=11;
        }
        else {
            zinc=8;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateStr = formatter.format(date);
        Date currentDate = formatter.parse(currentDateStr);

        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(user,currentDate,calories,carbohydrate,cholesterol,sodium,calcium,potassium,protein,sugar,vit_a,vit_b2,vit_c,vit_d,zinc);

        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


}
