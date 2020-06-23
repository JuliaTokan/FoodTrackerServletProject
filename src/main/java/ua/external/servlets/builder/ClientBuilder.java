package ua.external.servlets.builder;

import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;

/**
 * Provide the construction of a complex object
 */
public class ClientBuilder {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private Double height;
    private Double weight;
    private NutritionGoal nutritionGoal;
    private Activity activity;

    public ClientBuilder() {
    }

    public ClientBuilder(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.gender = client.getGender();
        this.age = client.getAge();
        this.height = client.getHeight();
        this.weight = client.getWeight();
        this.nutritionGoal = client.getNutritionGoal();
        this.activity = client.getActivity();
    }

    public ClientBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public ClientBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public ClientBuilder setHeight(Double height) {
        this.height = height;
        return this;
    }

    public ClientBuilder setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public ClientBuilder setNutritionGoal(NutritionGoal nutritionGoal) {
        this.nutritionGoal = nutritionGoal;
        return this;
    }

    public ClientBuilder setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Client createClient() {
        return new Client(id, name, gender, age, height, weight, nutritionGoal, activity);
    }
}