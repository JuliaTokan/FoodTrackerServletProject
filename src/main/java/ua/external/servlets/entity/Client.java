package ua.external.servlets.entity;

import ua.external.servlets.util.nutrition.NutritionCalculator;

/**
 * A model class for client database table
 */
public class Client extends Entity {
    private String name;

    private Gender gender;

    private Integer age;

    private Double height;
    private Double weight;

    private NutritionGoal nutritionGoal;
    private Activity activity;

    private Integer calories;
    private Double protein;
    private Double fats;
    private Double carbohydrates;

    public Client() {
    }

    public Client(Long id, String name, Gender gender, Integer age, Double height, Double weight, NutritionGoal nutritionGoal, Activity activity) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.nutritionGoal = nutritionGoal;
        this.activity = activity;

        //calculate calories ...
        Integer dailyCalories = NutritionCalculator.calculateCalories(gender, weight, height, age, activity, nutritionGoal);
        this.calories = dailyCalories;
        this.protein = NutritionCalculator.calculateProtein(nutritionGoal, dailyCalories);
        this.fats = NutritionCalculator.calculateFats(nutritionGoal, dailyCalories);
        this.carbohydrates = NutritionCalculator.calculateCarbohydrates(nutritionGoal, dailyCalories);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public NutritionGoal getNutritionGoal() {
        return nutritionGoal;
    }

    public void setNutritionGoal(NutritionGoal nutritionGoal) {
        this.nutritionGoal = nutritionGoal;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


}
