package ua.external.servlets.entity;

/**
 * A model class for user-account database table
 */
public class User extends Entity{
    private String login;
    private String password;
    private UserRole role;
    private Long client_id;

    public User(){}

    public User(Long id, String login, String password, UserRole role, Long client_id) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.client_id = client_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClientId(Long client_id) {
        this.client_id = client_id;
    }
}
