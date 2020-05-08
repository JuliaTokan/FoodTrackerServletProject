package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.external.servlets.entity.Client;
import ua.external.servlets.pool.ConnectionPool;
import ua.external.servlets.service.ServiceException;

import java.io.FileReader;

import static org.junit.Assert.*;

public class ClientServiceTest {
    private final static Logger logger = LogManager.getLogger();
    ClientService clientService;

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in clientServiceTest");
        clientService = new ClientService();
        //RunScript.execute(ConnectionPool.getInstance().getConnection(), new FileReader("src/test/resources/db/init.sql"));
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in clientServiceTest");
        clientService = null;
        //RunScript.execute(ConnectionPool.getInstance().getConnection(), new FileReader("src/test/resources/db/delete.sql"));
    }

    @Test
    public void createClient() {
    }

    @Test
    public void updateClient() {
    }

    @Test
    public void findClientById() throws ServiceException {
        Client client = clientService.findClientById(new Long(1)).get();
    }
}