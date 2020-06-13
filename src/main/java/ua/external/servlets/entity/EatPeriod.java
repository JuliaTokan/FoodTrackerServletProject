package ua.external.servlets.entity;

import ua.external.servlets.entity.Entity;

/**
 * A model class for eat period database table
 */
public class EatPeriod extends Entity {
    private String period;

    public EatPeriod() {
    }

    public EatPeriod(Long id, String period) {
        super(id);
        this.period = period;
    }

    public EatPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
