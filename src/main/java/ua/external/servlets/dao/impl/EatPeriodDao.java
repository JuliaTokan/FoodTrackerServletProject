package ua.external.servlets.dao.impl;

import ua.external.servlets.dao.AbstractDao;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IEatPeriodDao;
import ua.external.servlets.dao.TableColumn;
import ua.external.servlets.entity.EatPeriod;

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
public class EatPeriodDao extends AbstractDao<Long, EatPeriod> implements IEatPeriodDao {
    private final static String SQL_SELECT_ALL_EAT_PERIODS = "SELECT * FROM eatPeriods";
    private final static String SQL_SELECT_EAT_PERIOD_BY_ID = "SELECT * FROM eatPeriods WHERE id = ?";

    @Override
    public List<EatPeriod> findAll() throws DaoException {
        List<EatPeriod> eatPeriodList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EAT_PERIODS);
            while (resultSet.next()) {
                EatPeriod eatPeriod = extractEatPeriod(resultSet);
                eatPeriodList.add(eatPeriod);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return eatPeriodList;
    }

    @Override
    public Optional<EatPeriod> findById(Long id) throws DaoException {
        EatPeriod eatPeriod = null;
        Optional<EatPeriod> eatPeriodOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_EAT_PERIOD_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                eatPeriod = extractEatPeriod(resultSet);
            }
            eatPeriodOptional = Optional.ofNullable(eatPeriod);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return eatPeriodOptional;
    }

    @Override
    public boolean create(EatPeriod entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(EatPeriod entity) throws DaoException {
        return false;
    }

    private EatPeriod extractEatPeriod(ResultSet resultSet) throws SQLException {
        EatPeriod eatPeriod = new EatPeriod();
        eatPeriod.setId(resultSet.getLong(TableColumn.EAT_PERIOD_ID));
        eatPeriod.setPeriod(resultSet.getString(TableColumn.EAT_PERIOD));
        return eatPeriod;
    }
}
