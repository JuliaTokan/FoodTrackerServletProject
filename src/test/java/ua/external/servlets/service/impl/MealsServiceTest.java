package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.impl.MealsDao;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.service.ServiceException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MealsServiceTest {
    private final static Logger logger = LogManager.getLogger();
    MealsService mealsService;
    MealsDao mealsDao;
    Connection connection = Mockito.spy(Connection.class);

    private static final Long ID = new Long(12);
    private static final Meals meals = new Meals();;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in mealsServiceTest");
        mealsService = Mockito.spy(MealsService.class);

        mealsDao = Mockito.spy(MealsDao.class);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in mealsServiceTest");
        mealsService = null;
        mealsDao = null;
    }

    @Test(expected = ServiceException.class)
    public void createMeals() throws ServiceException {
        Mockito.doReturn(new ServiceException()).when(mealsService).createMeals(meals);
        mealsService.createMeals(meals);
    }

    @Test
    public void getAllMealForUser() throws ServiceException {
        List<Meals> meals = new ArrayList<>();
        Mockito.doReturn(meals).when(mealsService).getAllMealForUser(ID);
        Assert.assertNotNull(mealsService.getAllMealForUser(ID));
    }

    @Test
    public void deleteMealsById() throws ServiceException, DaoException {
        Mockito.doReturn(true).when(mealsDao).delete(ID);
        Assert.assertTrue(mealsService.deleteMealsById(ID));
    }
}