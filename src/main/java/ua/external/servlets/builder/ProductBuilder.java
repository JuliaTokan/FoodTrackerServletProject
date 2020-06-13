package ua.external.servlets.builder;

import ua.external.servlets.entity.Product;

/**
 * Provide the construction of a complex object
 */
public class ProductBuilder {
    private Long id;
    private Long user_id;
    private String name;
    private Integer calories;
    private Double protein;
    private Double fats;
    private Double carbohydrates;
    private Boolean common = false;
    private Boolean deleted = false;

    public ProductBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setUserId(Long user_id) {
        this.user_id = user_id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCalories(Integer calories) {
        this.calories = calories;
        return this;
    }

    public ProductBuilder setProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    public ProductBuilder setFats(Double fats) {
        this.fats = fats;
        return this;
    }

    public ProductBuilder setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    public ProductBuilder setCommon(Boolean common) {
        this.common=common;
        return this;
    }

    public ProductBuilder setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Product createProduct() {
        return new Product(id, user_id, name, calories, protein, fats, carbohydrates, common, deleted);
    }
}