package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.pool.ConnectionPool;
import ua.external.servlets.service.ServiceException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private final static Logger logger = LogManager.getLogger();
    private final static String LOGIN = "yulia.tokan.11@gmail.com";
    private final static String LOGIN_DONT_EXIST = "notexist@gmail789.com";
    UserService userService;

    private static final User user = new User();
    private static final Long ID = new Long(4);

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in userServiceTest");
        userService = Mockito.spy(UserService.class);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in userServiceTest");
        userService = null;
    }

    @Test
    public void createUser() throws ServiceException {
        Mockito.doReturn(true).when(userService).createUser(user);
        assertTrue(userService.createUser(user));
    }


    @Test
    public void findUserByLogin() throws ServiceException {
        Mockito.doReturn(user).when(userService).findUserByLogin(LOGIN);
        assertEquals(user, userService.findUserByLogin(LOGIN));
    }

    @Test
    public void countCalories() throws ServiceException {
        Mockito.doReturn(1500).when(userService).countCalories(user);
        Integer expected = 1500;
        assertEquals(expected, userService.countCalories(user));
    }

    @Test
    public void countProtein() throws ServiceException {
        Mockito.doReturn(500).when(userService).countProtein(user);
        Integer expected = 500;
        assertEquals(expected, userService.countProtein(user));
    }

    @Test
    public void countFats() throws ServiceException {
        Mockito.doReturn(400).when(userService).countFats(user);
        Integer expected = 400;
        assertEquals(expected, userService.countFats(user));
    }

    @Test
    public void countCarbohydrates() throws ServiceException {
        Mockito.doReturn(100).when(userService).countCarbohydrates(user);
        Integer expected = 100;
        assertEquals(expected, userService.countCarbohydrates(user));
    }

    @Test
    public void findAllUserProducts() throws ServiceException {
        List<Product> products = new ArrayList<>();
        Mockito.doReturn(products).when(userService).findAllUserProducts(user);
        Assert.assertNotNull(userService.findAllUserProducts(user));
    }
}