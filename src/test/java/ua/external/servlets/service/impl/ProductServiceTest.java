package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductServiceTest {
    private final static Logger logger = LogManager.getLogger();
    ProductService productService;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in productServiceTest");
        productService = new ProductService();
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in productServiceTest");
        productService = null;
    }

    @Test
    public void createProduct() {
    }

    @Test
    public void findAllProductsForUser() {
    }

    @Test
    public void findProductById() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProductById() {
    }

    @Test
    public void findAllProductsForUserByName() {
    }
}