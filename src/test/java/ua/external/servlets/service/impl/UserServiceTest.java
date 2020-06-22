package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.IUserDao;
import ua.external.servlets.dao.impl.UserDao;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.pool.ConnectionPool;
import ua.external.servlets.service.ServiceException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final static Logger logger = LogManager.getLogger();
    private final static String LOGIN = "yulia.tokan.11@gmail.com";
    private final static String LOGIN_DONT_EXIST = "notexist@gmail789.com";

    UserService userService;

    IUserDao userDao;

    private static final User user = new UserBuilder().setLogin("test.user@gmail.com").setPassword("qwe12345").createUser();
    private static final User emptyUser = new User();
    private static final Long ID = new Long(4);

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in userServiceTest");
        userService = new UserService();
        userDao = mock(IUserDao.class);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in userServiceTest");
        userService = null;
    }

    @Test
    public void createUser() throws ServiceException {
        assertTrue(userService.createUser(user));
    }

    @Test(expected = ServiceException.class)
    public void createUserWithException() throws ServiceException {
        assertTrue(userService.createUser(emptyUser));
    }


    @Test
    public void findUserByLogin() throws ServiceException, DaoException {
        assertNotNull(userService.findUserByLogin(LOGIN).get());
    }

    @Test
    public void countCalories() throws ServiceException {
        Mockito.doReturn(1500).when(userService).countCalories(user);
        Integer expected = 1500;
        assertEquals(expected, userService.countCalories(user));
    }

    @Test
    public void countProtein() throws ServiceException {
        Integer expected = 500;
        assertEquals(expected, userService.countProtein(user));
    }

    @Test
    public void countFats() throws ServiceException {
        Integer expected = 400;
        assertEquals(expected, userService.countFats(user));
    }

    @Test
    public void countCarbohydrates() throws ServiceException {
        Integer expected = 100;
        assertEquals(expected, userService.countCarbohydrates(user));
    }
}