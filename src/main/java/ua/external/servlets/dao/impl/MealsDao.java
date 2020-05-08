package ua.external.servlets.dao.impl;

import ua.external.servlets.builder.MealsBuilder;
import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IMealsDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.EatPeriod;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.Product;
import ua.external.servlets.service.impl.EatPeriodService;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealsDao extends AbstractDao<Long, Meals> implements IMealsDao {
    private final static String SQL_SELECT_ALL_MEALS = "SELECT * FROM meals";
    private final static String SQL_SELECT_MEALS_BY_ID = "SELECT * FROM meals WHERE id = ?";
    private final static String SQL_SELECT_MEALS_BY_USER = "SELECT * FROM meals WHERE user_id = ?";
    private final static String SQL_CREATE_MEALS = "INSERT INTO meals (user_id, product_id, weight, eat_period_id, date) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_MEALS = "UPDATE meals SET user_id= ?, product_id= ?, weight= ?, eat_period_id= ?, date= ? WHERE id= ?";
    private final static String SQL_DELETE_MEAL = "DELETE FROM meals WHERE id= ?";

    private ProductService productService = new ProductService();
    private EatPeriodService eatPeriodService = new EatPeriodService();

    @Override
    public List<Meals> findAll() throws DaoException {
        List<Meals> mealsList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MEALS);
            while (resultSet.next()) {
                Meals meals = extractMeals(resultSet);
                mealsList.add(meals);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return mealsList;
    }

    @Override
    public Optional<Meals> findById(Long id) throws DaoException {
        Meals meals = null;
        Optional<Meals> mealsOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_MEALS_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                meals = extractMeals(resultSet);
            }
            mealsOptional = Optional.ofNullable(meals);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return mealsOptional;
    }

    @Override
    public List<Meals> findAllByUser(Long id) throws DaoException {
        List<Meals> mealsList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_MEALS_BY_USER);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Meals meals = extractMeals(resultSet);
                mealsList.add(meals);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return mealsList;
    }

    @Override
    public boolean create(Meals meals) throws DaoException {
        boolean created;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_MEALS);
            statement.setLong(1, meals.getUserId());
            statement.setLong(2, meals.getProduct().getId());
            statement.setInt(3, meals.getWeight());
            statement.setLong(4, meals.getEatPeriod().getId());
            statement.setTimestamp(5, meals.getDate());
            int updatedRows = statement.executeUpdate();
            created = updatedRows != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return created;
    }

    @Override
    public boolean update(Meals meals) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_MEALS);
            statement.setLong(1, meals.getUserId());
            statement.setLong(2, meals.getProduct().getId());
            statement.setInt(3, meals.getWeight());
            statement.setLong(4, meals.getEatPeriod().getId());
            statement.setTimestamp(5, meals.getDate());
            statement.setLong(6, meals.getId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    public boolean delete(Long id) throws DaoException {
        PreparedStatement statement = null;
        boolean deleted;
        try {
            statement = connection.prepareStatement(SQL_DELETE_MEAL);
            statement.setLong(1, id);
            deleted = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return deleted;
    }

    private Meals extractMeals(ResultSet resultSet) throws SQLException {
        Long productId = resultSet.getLong(TableColumn.MEAL_PRODUCT_ID);
        Long earPeriodId = resultSet.getLong(TableColumn.MEAL_EAT_PERIOD);

        Product product = null;
        EatPeriod eatPeriod = null;
        try {
            product = productService.findProductById(productId).get();
            eatPeriod = eatPeriodService.findEatPeriodById(earPeriodId).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Meals meals = new MealsBuilder()
                .setId(resultSet.getLong(TableColumn.MEAL_ID))
                .setUserId(resultSet.getLong(TableColumn.MEAL_USER_ID))
                .setProduct(product)
                .setWeight(resultSet.getInt(TableColumn.MEAL_PRODUCT_WEIGHT))
                .setEatPeriod(eatPeriod)
                .setDate(resultSet.getTimestamp(TableColumn.MEAL_DATE))
                .createMeals();

        return meals;
    }
}
