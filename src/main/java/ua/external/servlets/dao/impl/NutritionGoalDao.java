package ua.external.servlets.dao.impl;

import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.INutritionGoalDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.NutritionGoal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NutritionGoalDao extends AbstractDao<Long, NutritionGoal> implements INutritionGoalDao {
    private final static String SQL_SELECT_ALL_NUTR_GOALS = "SELECT * FROM nutritionGoals";
    private final static String SQL_SELECT_NUTR_GOAL_BY_ID = "SELECT * FROM nutritionGoals WHERE id = ?";

    @Override
    public List<NutritionGoal> findAll() throws DaoException {
        List<NutritionGoal> nutritionGoalList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_NUTR_GOALS);
            while (resultSet.next()) {
                NutritionGoal nutritionGoal = extractNutritionGoal(resultSet);
                nutritionGoalList.add(nutritionGoal);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return nutritionGoalList;
    }

    @Override
    public Optional<NutritionGoal> findById(Long id) throws DaoException {
        NutritionGoal nutritionGoal = null;
        Optional<NutritionGoal> nutritionGoalOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_NUTR_GOAL_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nutritionGoal = extractNutritionGoal(resultSet);
            }
            nutritionGoalOptional = Optional.ofNullable(nutritionGoal);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return nutritionGoalOptional;
    }

    @Override
    public boolean create(NutritionGoal entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(NutritionGoal entity) throws DaoException {
        return false;
    }

    private NutritionGoal extractNutritionGoal(ResultSet resultSet) throws SQLException {
        NutritionGoal nutritionGoal = new NutritionGoal();
        nutritionGoal.setId(resultSet.getLong(TableColumn.NUTR_GOAL_ID));
        nutritionGoal.setGoal(resultSet.getString(TableColumn.NUTR_GOAL));
        nutritionGoal.setCoefficient(resultSet.getDouble(TableColumn.NUTR_GOAL_COEFFICIENT));
        return nutritionGoal;
    }
}
