package ua.external.servlets.dao.impl;

import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IUserRoleDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for the PostgreSQL DB
 */
public class UserRoleDao extends AbstractDao<Long, UserRole> implements IUserRoleDao {
    private final static String SQL_SELECT_USER__ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";

    @Override
    public List<UserRole> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<UserRole> findById(Long id) throws DaoException {
        UserRole userRole = null;
        Optional<UserRole> userRoleOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER__ROLE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRole = extractUserRole(resultSet);
            }
            userRoleOptional = Optional.ofNullable(userRole);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userRoleOptional;
    }

    @Override
    public boolean create(UserRole entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(UserRole entity) throws DaoException {
        return false;
    }

    private UserRole extractUserRole(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getLong(TableColumn.USER_ROLE_ID));
        userRole.setRole(resultSet.getString(TableColumn.USER_ROLE));
        return userRole;
    }

}
