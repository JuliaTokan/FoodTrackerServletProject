package ua.external.servlets.dao;

import ua.external.servlets.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private Connection connection;

    public EntityTransaction() {
    }

    public void beginNoTransaction(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    public void begin(AbstractDao dao, AbstractDao... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            //logger.log(Level.ERROR, " Error executing query ", e);
        }
        dao.setConnection(connection);
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                //logger.log(Level.ERROR, " Error changing autocommit status",  e);
            }
            ConnectionPool.getInstance().returnConnection(connection);
            connection = null;
        }
    }

    public void endNoTransaction() {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
            connection = null;
        }
    }
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Commit transaction error", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Rollback transaction error", e);
        }
    }
}
