package ua.external.servlets.dao.impl;

import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IActivityDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.Activity;

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
public class ActivityDao extends AbstractDao<Long, Activity> implements IActivityDao {
    private final static String SQL_SELECT_ALL_ACTIVITIES = "SELECT * FROM activities";
    private final static String SQL_SELECT_ACTIVITY_BY_ID = "SELECT * FROM activities WHERE id = ?";

    @Override
    public List<Activity> findAll() throws DaoException {
        List<Activity> activityList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ACTIVITIES);
            while (resultSet.next()) {
                Activity activity = extractActivity(resultSet);
                activityList.add(activity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return activityList;
    }

    @Override
    public Optional<Activity> findById(Long id) throws DaoException {
        Activity activity = null;
        Optional<Activity> activityOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTIVITY_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                activity = extractActivity(resultSet);
            }
            activityOptional = Optional.ofNullable(activity);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return activityOptional;
    }

    @Override
    public boolean create(Activity entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Activity entity) throws DaoException {
        return false;
    }

    private Activity extractActivity(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getLong(TableColumn.ACTIVITY_ID));
        activity.setActivity(resultSet.getString(TableColumn.ACTIVITY));
        activity.setCoefficient(resultSet.getDouble(TableColumn.ACTIVITY_COEFFICIENT));
        return activity;
    }
}
