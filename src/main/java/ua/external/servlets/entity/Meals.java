package ua.external.servlets.entity;

import ua.external.servlets.entity.enums.TimeMeals;

import java.sql.Timestamp;

public class Meals extends Entity{
    private Long user_id;
    private Product product;
    private Integer weight;
    private EatPeriod eatPeriod;
    private Timestamp date;

    public Meals(){}

    public Meals(Long id, Long user_id, Product product, Integer weight, EatPeriod eatPeriod, Timestamp date) {
        super(id);
        this.user_id = user_id;
        this.product = product;
        this.weight = weight;
        this.eatPeriod = eatPeriod;
        this.date = date;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public EatPeriod getEatPeriod() {
        return eatPeriod;
    }

    public void setEatPeriod(EatPeriod eatPeriod) {
        this.eatPeriod = eatPeriod;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
