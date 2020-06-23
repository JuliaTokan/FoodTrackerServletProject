package ua.external.servlets.dao.impl;

import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IUserDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserRoleService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for the PostgreSQL DB
 */
public class UserDao extends AbstractDao<Long, User> implements IUserDao {
    private final static String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private final static String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private final static String SQL_CREATE_USER = "INSERT INTO users (login, password, role_id) VALUES (?, ?, ?) RETURNING id";
    private final static String SQL_UPDATE_USER = "UPDATE users SET login= ?, password= ?, role_id= ?, client_id= ? WHERE id= ?";
    private final static String SQL_IS_EXIST_USER = "SELECT id FROM users WHERE login = ? and password = ?";

    private UserRoleService userRoleService = new UserRoleService();

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = extractUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        User user = null;
        Optional<User> userOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
            userOptional = Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userOptional;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean created = false;
        Long userID;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getRole().getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                userID = resultSet.getLong("id");
                user.setId(userID);
                created = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return created;
    }

    @Override
    public boolean update(User user) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getRole().getId());
            statement.setLong(4, user.getClient_id());
            statement.setLong(5, user.getId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        User user = null;
        Optional<User> userOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
            userOptional = Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userOptional;
    }

    @Override
    public boolean userIsExist(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        boolean exist = false;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(TableColumn.USER_ID);
                exist = id == null ? false : true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return exist;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        Long userRoleId = Long.parseLong(resultSet.getString(TableColumn.ROLE));

        UserRole userRole = null;
        try {
            userRole = userRoleService.findUserRoleById(userRoleId).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        User user = new UserBuilder()
                .setId(resultSet.getLong(TableColumn.USER_ID))
                .setLogin(resultSet.getString(TableColumn.USER_LOGIN))
                .setPassword(resultSet.getString(TableColumn.USER_PASSWORD))
                .setRole(userRole)
                .setClient_id(resultSet.getLong(TableColumn.USER_CLIENT_ID))
                .createUser();
        return user;
    }
}
