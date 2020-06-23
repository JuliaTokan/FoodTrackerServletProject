package ua.external.servlets.entity;

/**
 * A model class for nutrition goal database table
 */
public class NutritionGoal extends Entity {
    private String goal;
    private Double coefficient;

    public NutritionGoal() {
    }

    public NutritionGoal(Long id, String goal, Double coefficient) {
        super(id);
        this.goal = goal;
        this.coefficient = coefficient;
    }

    public NutritionGoal(String goal, Double coefficient) {
        super(null);
        this.goal = goal;
        this.coefficient = coefficient;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
