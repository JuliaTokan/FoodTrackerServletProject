package ua.external.servlets.dao.impl;

import ua.external.servlets.builder.ProductBuilder;
import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IProductDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao extends AbstractDao<Long, Product> implements IProductDao {

    private final static String SQL_SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    private final static String SQL_SELECT_ALL_PRODUCTS_BY_USER = "SELECT * FROM products WHERE (common = true OR user_id= ?)";
    private final static String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final static String SQL_CREATE_PRODUCT = "INSERT INTO products (name, user_id, calories, protein, fats, carbohydrates, common) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
    private final static String SQL_UPDATE_PRODUCT = "UPDATE products SET name= ?, calories= ?, protein= ?, fats= ?, carbohydrates= ?, common= ? WHERE id= ?";
    private final static String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE id= ?";
    private final static String SQL_SELECT_USER_PRODUCT = "SELECT * FROM users INNER JOIN meals ON users.id = meals.user_id INNER JOIN products ON meals.product_id = products.id WHERE users.id = ?";
    private final static String SQL_SELECT_PRODUCTS_BY_NAME = "SELECT * FROM products WHERE (name LIKE ? AND (common = true OR user_id= ?))";

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> productList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
            while (resultSet.next()) {
                Product product = extractProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return productList;
    }

    @Override
    public List<Product> findAllForUser(Long id) throws DaoException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCTS_BY_USER);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws DaoException {
        Product product = null;
        Optional<Product> productOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = extractProduct(resultSet);
            }
            productOptional = Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return productOptional;
    }

    @Override
    public boolean create(Product product) throws DaoException {
        boolean created = false;
        Long productId;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setLong(2, product.getUserId());
            statement.setInt(3, product.getCalories());
            statement.setDouble(4, product.getProtein());
            statement.setDouble(5, product.getFats());
            statement.setDouble(6, product.getCarbohydrates());
            statement.setBoolean(7, product.getCommon());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null && resultSet.next()) {
                productId = resultSet.getLong("id");
                product.setId(productId);
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
    public boolean update(Product product) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getCalories());
            statement.setDouble(3, product.getProtein());
            statement.setDouble(4, product.getFats());
            statement.setDouble(5, product.getCarbohydrates());
            statement.setBoolean(6, product.getCommon());
            statement.setLong(7, product.getId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        PreparedStatement statement = null;
        boolean deleted;
        try {
            statement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            statement.setLong(1, id);
            deleted = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return deleted;
    }

    @Override
    public List<Product> findUsersProducts(Long id) throws DaoException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_PRODUCT);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return productList;
    }

    @Override
    public List<Product> findAllForUserByName(Long id, String name) throws DaoException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCTS_BY_NAME);
            statement.setString(1, name+'%');
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return productList;
    }

    private Product extractProduct(ResultSet resultSet) throws SQLException {
        Product product = new ProductBuilder()
                .setId(resultSet.getLong(TableColumn.PRODUCT_ID))
                .setUserId(resultSet.getLong(TableColumn.PRODUCT_USER_ID))
                .setName(resultSet.getString(TableColumn.PRODUCT_NAME))
                .setCalories(resultSet.getInt(TableColumn.PRODUCT_CALORIES))
                .setProtein(resultSet.getDouble(TableColumn.PRODUCT_PROTEIN))
                .setFats(resultSet.getDouble(TableColumn.PRODUCT_FATS))
                .setCarbohydrates(resultSet.getDouble(TableColumn.PRODUCT_CARBOHYDRATES))
                .setCommon(resultSet.getBoolean(TableColumn.PRODUCT_COMMON))
                .createProduct();
        return product;
    }
}
