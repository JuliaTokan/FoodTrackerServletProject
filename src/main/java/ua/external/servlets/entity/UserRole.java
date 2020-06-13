package ua.external.servlets.entity;

/**
 * A model class for user role database table
 */
public class UserRole extends Entity {
    private String role;

    public UserRole() {
    }

    public UserRole(Long id, String role) {
        super(id);
        this.role = role;
    }

    public UserRole(String role) {
        super(null);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
