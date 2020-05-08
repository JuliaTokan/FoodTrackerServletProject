package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MealsServiceTest {
    private final static Logger logger = LogManager.getLogger();
    MealsService mealsService;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in mealsServiceTest");
        mealsService = new MealsService();
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in mealsServiceTest");
        mealsService = null;
    }

    @Test
    public void createMeals() {
    }

    @Test
    public void getAllMealForUser() {
    }

    @Test
    public void deleteMealsById() {
    }
}