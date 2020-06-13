package ua.external.servlets.entity;

/**
 * A model class for product database table
 */
public class Product extends Entity{
    private Long user_id;
    private String name;
    private Integer calories;

    private Double protein;
    private Double fats;
    private Double carbohydrates;

    private Boolean common;
    private Boolean deleted;

    public Product(){}

    public Product(Long id, Long user_id, String name, Integer calories, Double protein, Double fats, Double carbohydrates, Boolean common, Boolean deleted) {
        super(id);
        this.user_id = user_id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.common = common;
        this.deleted = deleted;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getCommon() {
        return common;
    }

    public void setCommon(Boolean common) {
        this.common = common;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
