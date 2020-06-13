package ua.external.servlets.dao.impl;

import ua.external.servlets.builder.ClientBuilder;
import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IClientDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.impl.ActivityService;
import ua.external.servlets.service.impl.GenderService;
import ua.external.servlets.service.impl.NutritionGoalService;
import ua.external.servlets.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for the PostgreSQL DB
 */
public class ClientDao extends AbstractDao<Long, Client> implements IClientDao {
    private final static String SQL_SELECT_ALL_CLIENTS = "SELECT * FROM clients";
    private final static String SQL_SELECT_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private final static String SQL_CREATE_CLIENT = "INSERT INTO clients (name, gender_id, age, height, weight, nutritiongoal_id, activity_id, calories, protein, fats, carbohydrates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
    private final static String SQL_UPDATE_CLIENT = "UPDATE clients SET name= ?, gender_id=?, age=?, height= ?, weight= ?, nutritiongoal_id= ?, activity_id= ?, calories= ?, protein= ?, fats= ?, carbohydrates= ? WHERE id= ?";

    private GenderService genderService = new GenderService();
    private ActivityService activityService = new ActivityService();
    private NutritionGoalService nutritionGoalService = new NutritionGoalService();

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientList;
    }

    @Override
    public Optional<Client> findById(Long id) throws DaoException {
        Client client = null;
        Optional<Client> clientOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = extractClient(resultSet);
            }
            clientOptional = Optional.ofNullable(client);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientOptional;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        boolean created = false;
        Long clientID;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_CLIENT);
            statement.setString(1, client.getName());
            statement.setLong(2, client.getGender().getId());
            statement.setInt(3, client.getAge());
            statement.setDouble(4, client.getHeight());
            statement.setDouble(5, client.getWeight());
            statement.setLong(6, client.getNutritionGoal().getId());
            statement.setLong(7, client.getActivity().getId());
            statement.setInt(8, client.getCalories());
            statement.setDouble(9, client.getProtein());
            statement.setDouble(10, client.getFats());
            statement.setDouble(11, client.getCarbohydrates());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null && resultSet.next()) {
                clientID = resultSet.getLong("id");
                client.setId(clientID);
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
    public boolean update(Client client) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CLIENT);
            statement.setString(1, client.getName());
            statement.setLong(2, client.getGender().getId());
            statement.setInt(3, client.getAge());
            statement.setDouble(4, client.getHeight());
            statement.setDouble(5, client.getWeight());
            statement.setLong(6, client.getNutritionGoal().getId());
            statement.setLong(7, client.getActivity().getId());
            statement.setInt(8, client.getCalories());
            statement.setDouble(9, client.getProtein());
            statement.setDouble(10, client.getFats());
            statement.setDouble(11, client.getCarbohydrates());
            statement.setLong(12, client.getId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    private Client extractClient(ResultSet resultSet) throws SQLException {
        Long genderId = Long.parseLong(resultSet.getString(TableColumn.CLIENT_GENDER));
        Long activityId = Long.parseLong(resultSet.getString(TableColumn.CLIENT_ACTIVITY));
        Long nutrGoalId = Long.parseLong(resultSet.getString(TableColumn.CLIENT_NUTRITION_GOAL));

        Gender gender = null;
        Activity activity = null;
        NutritionGoal nutritionGoal = null;
        try {
            gender = genderService.findGenderById(genderId).get();
            activity = activityService.findActivityById(activityId).get();
            nutritionGoal = nutritionGoalService.findNutritionGoalById(nutrGoalId).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Client client = new ClientBuilder().
                setId(resultSet.getLong(TableColumn.CLIENT_ID))
                .setName(resultSet.getString(TableColumn.CLIENT_NAME))
                .setGender(gender)
                .setAge(resultSet.getInt(TableColumn.CLIENT_AGE))
                .setHeight(resultSet.getDouble(TableColumn.CLIENT_HEIGHT))
                .setWeight(resultSet.getDouble(TableColumn.CLIENT_WEIGHT))
                .setNutritionGoal(nutritionGoal)
                .setActivity(activity)
                .createClient();
        client.setCalories(resultSet.getInt(TableColumn.CLIENT_CALORIES));
        client.setProtein(resultSet.getDouble(TableColumn.CLIENT_PROTEIN));
        client.setFats(resultSet.getDouble(TableColumn.CLIENT_FATS));
        client.setCarbohydrates(resultSet.getDouble(TableColumn.CLIENT_CARBOHYDRATES));
        return client;
    }
}
