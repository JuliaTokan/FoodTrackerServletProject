package ua.external.servlets.entity;

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
}
