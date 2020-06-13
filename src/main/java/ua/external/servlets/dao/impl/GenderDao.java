package ua.external.servlets.dao.impl;

import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IGenderDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.Gender;

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
public class GenderDao extends AbstractDao<Long, Gender> implements IGenderDao {
    private final static String SQL_SELECT_ALL_GENDERS = "SELECT * FROM genders";
    private final static String SQL_SELECT_GENDER_BY_ID = "SELECT * FROM genders WHERE id = ?";

    @Override
    public List<Gender> findAll() throws DaoException {
        List<Gender> genderList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_GENDERS);
            while (resultSet.next()) {
                Gender gender = extractGender(resultSet);
                genderList.add(gender);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return genderList;
    }

    @Override
    public Optional<Gender> findById(Long id) throws DaoException {
        Gender gender = null;
        Optional<Gender> genderOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_GENDER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gender = extractGender(resultSet);
            }
            genderOptional = Optional.ofNullable(gender);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return genderOptional;
    }

    @Override
    public boolean create(Gender entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Gender entity) throws DaoException {
        return false;
    }

    private Gender extractGender(ResultSet resultSet) throws SQLException {
        Gender gender = new Gender();
        gender.setId(resultSet.getLong(TableColumn.GENDER_ID));
        gender.setGender(resultSet.getString(TableColumn.GENDER));
        return gender;
    }
}
