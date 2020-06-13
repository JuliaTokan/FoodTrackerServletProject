package ua.external.servlets.entity;

/**
 * A model class for gender database table
 */
public class Gender extends Entity {
    private String gender;

    public Gender() {
    }

    public Gender(Long id, String gender) {
        super(id);
        this.gender = gender;
    }

    public Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
