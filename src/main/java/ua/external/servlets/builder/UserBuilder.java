package ua.external.servlets.builder;

import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;

/**
 * Provide the construction of a complex object
 */
public class UserBuilder {
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private Long client_id = null;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserBuilder setClient_id(Long client_id) {
        this.client_id = client_id;
        return this;
    }

    public User createUser() {
        return new User(id, login, password, role, client_id);
    }
}