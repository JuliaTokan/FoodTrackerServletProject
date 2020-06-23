package ua.external.servlets.pool;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPool} class has private Deque in which
 * Connections are stored.
 * The max amount of created connections is set from properties poolsize value.
 * The connection can be taken from the Deque and
 * released to it. If the Deque is empty, and the amount of
 * created connections is less then poolsize, new connection
 * is created and returned, otherwise its needed to wait when a connection
 * is released (returned to current connection poll).
 * Thread safe.
 *
 * @see ConnectionCreator
 */
public class ConnectionPool {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock connectionLock = new ReentrantLock();
    private static final ReentrantLock returnLock = new ReentrantLock();
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private Deque<Connection> connections;
    private Semaphore semaphore;
    private ConnectionCreator connectionCreator = new ConnectionCreator();
    private int connectionSize;

    private ConnectionPool() {
        readConnectionSizeFromProperties();
        initConnections();
        createConnections();
    }

    public static ConnectionPool getInstance() {
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    instance = new ConnectionPool();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initConnections() {
        connections = new ArrayDeque<>();
        semaphore = new Semaphore(connectionSize);
    }

    private void createConnections() {
        for (int i = 0; i < connectionSize; i++) {
            Connection connection = connectionCreator.createConnection();
            connections.push(connection);
        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException("Connections are not created!");
        }
    }

    private void readConnectionSizeFromProperties() {
        ResourceBundle resource = ResourceBundle.getBundle("db.database");
        String poolsize = resource.getString("poolsize");

        connectionSize = Integer.parseInt(poolsize);
    }

    public Connection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        } finally {
            connectionLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            returnLock.unlock();
        }
    }
}
