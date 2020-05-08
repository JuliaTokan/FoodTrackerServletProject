package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.pool.ConnectionPool;
import ua.external.servlets.service.ServiceException;

import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private final static Logger logger = LogManager.getLogger();
    private final static String LOGIN = "yulia.tokan.11@gmail.com";
    private final static String LOGIN_DONT_EXIST = "notexist@gmail789.com";
    UserService userService;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in userServiceTest");
        userService = new UserService();
        RunScript.execute(ConnectionPool.getInstance().getConnection(), new FileReader("src/test/resources/db/init.sql"));
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in userServiceTest");
        userService = null;
        RunScript.execute(ConnectionPool.getInstance().getConnection(), new FileReader("src/test/resources/db/delete.sql"));
    }

    @Test
    public void createUser() throws ServiceException {
    }


    @Test
    public void findUserByLogin() throws ServiceException {
        User user = userService.findUserByLogin("admin@gmail.com").get();
    }

    @Test
    public void countCalories() {
    }

    @Test
    public void countProtein() {
    }

    @Test
    public void countFats() {
    }

    @Test
    public void countCarbohydrates() {
    }

    @Test
    public void findAllUsers() {
    }

    @Test
    public void findAllUserProducts() {
    }
}