package ua.external.servlets.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidatorTest {
    private final static Logger logger = LogManager.getLogger();
    DataValidator validator;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in dataValidatorTest");
        validator = new DataValidator();
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in dataValidatorTest");
        validator = null;
    }

    @Test
    public void validateLogin() {
        String login = "yulia.tokan@gmail.com";
        assertTrue(DataValidator.validateLogin(login));
    }

    @Test
    public void validatePassword() {
        String password = "lololo12";
        assertTrue(DataValidator.validatePassword(password));
    }

    @Test
    public void validateName() {
        String name = "Y";
        assertFalse(DataValidator.validateName(name));
    }

    @Test
    public void validateAge() {
        Integer age = 500;
        assertFalse(DataValidator.validateAge(age));
    }

    @Test
    public void validateHeight() {
        Double height = 165.5;
        assertTrue(DataValidator.validateHeight(height));
    }

    @Test
    public void validateWeight() {
        Double weight = 65.3;
        assertTrue(DataValidator.validateWeight(weight));
    }

    @Test
    public void validateProductWeight() {
        Integer weight = 250;
        assertTrue(DataValidator.validateProductWeight(weight));
    }

    @Test
    public void validateCalories() {
        Integer calories = 256;
        assertTrue(DataValidator.validateCalories(calories));
    }

    @Test
    public void validateNutrition() {
        Double value = 56.4;
        assertTrue(DataValidator.validateNutrition(value));
    }
}