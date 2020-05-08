package ua.external.servlets.builder;

import ua.external.servlets.entity.EatPeriod;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.enums.TimeMeals;

import java.sql.Timestamp;

public class MealsBuilder {
    private Long id;
    private Long user_id;
    private Product product;
    private Integer weight;
    private EatPeriod eat_period;
    private Timestamp date;

    public MealsBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MealsBuilder setUserId(Long user_id) {
        this.user_id = user_id;
        return this;
    }

    public MealsBuilder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public MealsBuilder setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public MealsBuilder setEatPeriod(EatPeriod eat_period) {
        this.eat_period = eat_period;
        return this;
    }

    public MealsBuilder setDate(Timestamp date) {
        this.date = date;
        return this;
    }

    public Meals createMeals() {
        return new Meals(id, user_id, product, weight, eat_period, date);
    }
}