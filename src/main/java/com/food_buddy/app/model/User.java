package com.food_buddy.app.model;


import com.food_buddy.app.StringToListConverter;
import com.food_buddy.app.model.audit.DateAudit;
import com.food_buddy.app.repository.RecommendedNutrientIntakeRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String lastName;

    @NotNull
    private float height;

    @NotNull
    private float weight;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private Date date;

    @NotBlank
    @Size(max = 40)
    private String gender;


    @Convert(converter = StringToListConverter.class)
    private List<String> chronicDiseases;


//    @Convert(converter = StringToListConverter.class)
//    private List<String> foodAllergies;

    @NotBlank
    @Size(max = 40)
    private String smokingStatus;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


//    @Autowired
//    RecommendedNutrientIntakeRepository recommendedNutrientIntakeRepository;

    public User() {
    }

    public User(String username, String email, String password, String firstName, String lastName, float height, float weight, Date date, String gender, List<String> chronicDiseases, String smokingStatus) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.date = date;
        this.gender = gender;
        this.chronicDiseases = chronicDiseases;
        this.smokingStatus = smokingStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) throws ParseException {
//        this.height = height;
//
//        Date date = new Date();
//        long difference_In_Time = date.getTime() - this.date.getTime();
//        long age = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
//
//        double calories = 0;
//        if (this.gender == "Male"){
//            calories = 13.397*this.weight+ 4.799*height - 5.677*age + 88.362;
//        }
//        else{
//            calories = 9.247*this.weight + 3.098*height - 4.330*age + 447.593;
//        }
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//
//        RecommendedNutrientIntake recommendedNutrientIntake = recommendedNutrientIntakeRepository.findLatestRec(this.id);
//
//        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(this,currentDate,calories,recommendedNutrientIntake.getCarbohydrate_g(),recommendedNutrientIntake.getCholesterol_mg(),recommendedNutrientIntake.getSodium_mg(),recommendedNutrientIntake.getCalcium_mg(),recommendedNutrientIntake.getPotassium_mg(),recommendedNutrientIntake.getProtein_g(),recommendedNutrientIntake.getSugar_g(),recommendedNutrientIntake.getVitamin_A_mcg(),recommendedNutrientIntake.getVitamin_B2_mg(),recommendedNutrientIntake.getVitamin_C_mg(),recommendedNutrientIntake.getVitamin_D_IU(),recommendedNutrientIntake.getZinc_mg());
//
//        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) throws ParseException {
        this.weight = weight;

//        Date date = new Date();
//        long difference_In_Time = date.getTime() - this.date.getTime();
//        long age = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
//
//        double calories = 0;
//        if (this.gender == "Male"){
//            calories = 13.397*weight+ 4.799*this.height - 5.677*age + 88.362;
//        }
//        else{
//            calories = 9.247*weight + 3.098*this.height - 4.330*age + 447.593;
//        }
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//
//        RecommendedNutrientIntake recommendedNutrientIntake = recommendedNutrientIntakeRepository.findLatestRec(this.id);
//
//        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(this,currentDate,calories,recommendedNutrientIntake.getCarbohydrate_g(),recommendedNutrientIntake.getCholesterol_mg(),recommendedNutrientIntake.getSodium_mg(),recommendedNutrientIntake.getCalcium_mg(),recommendedNutrientIntake.getPotassium_mg(),recommendedNutrientIntake.getProtein_g(),recommendedNutrientIntake.getSugar_g(),recommendedNutrientIntake.getVitamin_A_mcg(),recommendedNutrientIntake.getVitamin_B2_mg(),recommendedNutrientIntake.getVitamin_C_mg(),recommendedNutrientIntake.getVitamin_D_IU(),recommendedNutrientIntake.getZinc_mg());
//        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(List<String> chronicDiseases) throws ParseException {
        this.chronicDiseases = chronicDiseases;
//
//        Date date = new Date();
//        long difference_In_Time = date.getTime() - this.date.getTime();
//        long age = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
//
//        double calcium=0;
//        if (this.chronicDiseases.contains("Arthritis") || this.chronicDiseases.contains("Heart Disease") ){
//            calcium=1500;
//        }
//        else if(age >= 70){
//            calcium=1200;
//        }
//        else{
//            calcium=1000;
//        }
//
//        double sodium=0;
//        if (this.chronicDiseases.contains("Arthritis") || this.chronicDiseases.contains("Heart Disease") || this.chronicDiseases.contains("Diabetes") || this.chronicDiseases.contains("Respiratory Disease") ){
//            sodium=1500;
//        }
//        else{
//            sodium=2300;
//        }
//
//        double carbohydrate=0;
//        if (this.chronicDiseases.contains("Heart Disease") || this.chronicDiseases.contains("Diabetes") ){
//            carbohydrate=70;
//        }
//        else{
//            carbohydrate=320;
//        }
//
//        double cholesterol=0;
//        if (this.chronicDiseases.contains("Heart Disease") ){
//            cholesterol=150;
//        }
//        else{
//            cholesterol=300;
//        }
//
//        double protein=0;
//        if (this.chronicDiseases.contains("Cancer") || this.chronicDiseases.contains("Heart Disease") || this.chronicDiseases.contains("Osteoporosis") || this.chronicDiseases.contains("Respiratory Disease") ){
//            protein=1.5*this.weight;
//        }
//        else{
//            protein=0.8*this.weight;
//        }
//
//        double sugar=0;
//        if (this.chronicDiseases.contains("Diabetes") && this.gender == "Male"){
//            sugar=25;
//        }
//        else if (this.chronicDiseases.contains("Diabetes") && this.gender == "Female"){
//            sugar=20;
//        }
//        else if (this.gender == "Male"){
//            sugar=36;
//        }
//        else {
//            sugar=24;
//        }
//
//        double vit_d=0;
//        if (this.chronicDiseases.contains("Cancer") || this.chronicDiseases.contains("Alzheimer") || this.chronicDiseases.contains("Osteoporosis")){
//            vit_d=1000;
//        }
//        else if (age >= 70){
//            vit_d=800;
//        }
//        else{
//            vit_d=600;
//        }
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//
//        RecommendedNutrientIntake recommendedNutrientIntake = recommendedNutrientIntakeRepository.findLatestRec(this.id);
//        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(this,currentDate,recommendedNutrientIntake.getEnergy_kcal(),carbohydrate,cholesterol,sodium,calcium,recommendedNutrientIntake.getPotassium_mg(),protein,sugar,recommendedNutrientIntake.getVitamin_A_mcg(),recommendedNutrientIntake.getVitamin_B2_mg(),recommendedNutrientIntake.getVitamin_C_mg(),vit_d,recommendedNutrientIntake.getZinc_mg());
//        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) throws ParseException {
        this.smokingStatus = smokingStatus;

//        double vit_c=0;
//        if (this.gender == "Male"){
//            vit_c=90;
//        }
//        else {
//            vit_c=75;
//        }
//
//        if (smokingStatus=="Yes"){
//            vit_c = vit_c + 35;
//        }
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateStr = formatter.format(date);
//        Date currentDate = formatter.parse(currentDateStr);
//
//        RecommendedNutrientIntake recommendedNutrientIntake = recommendedNutrientIntakeRepository.findLatestRec(this.id);
//
//        RecommendedNutrientIntake nutrientIntake = new RecommendedNutrientIntake(this,currentDate,recommendedNutrientIntake.getEnergy_kcal(),recommendedNutrientIntake.getCarbohydrate_g(),recommendedNutrientIntake.getCholesterol_mg(),recommendedNutrientIntake.getSodium_mg(),recommendedNutrientIntake.getCalcium_mg(),recommendedNutrientIntake.getPotassium_mg(),recommendedNutrientIntake.getProtein_g(),recommendedNutrientIntake.getSugar_g(),recommendedNutrientIntake.getVitamin_A_mcg(),recommendedNutrientIntake.getVitamin_B2_mg(),vit_c,recommendedNutrientIntake.getVitamin_D_IU(),recommendedNutrientIntake.getZinc_mg());
//        RecommendedNutrientIntake result1 = recommendedNutrientIntakeRepository.save(nutrientIntake);

    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
