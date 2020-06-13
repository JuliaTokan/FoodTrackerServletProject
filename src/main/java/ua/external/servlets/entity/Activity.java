package ua.external.servlets.entity;

import java.util.Objects;

/**
 * A model class for activity database table
 */
public class Activity extends Entity {
    private String activity;
    private Double coefficient;

    public Activity() {
    }

    public Activity(Long id, String activity, Double coefficient) {
        super(id);
        this.activity = activity;
        this.coefficient = coefficient;
    }

    public Activity(String activity, Double coefficient) {
        super(null);
        this.activity = activity;
        this.coefficient = coefficient;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity1 = (Activity) o;
        return Objects.equals(activity, activity1.activity) &&
                Objects.equals(coefficient, activity1.coefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activity, coefficient);
    }
}
