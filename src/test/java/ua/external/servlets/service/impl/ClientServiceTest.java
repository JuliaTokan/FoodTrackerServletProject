package ua.external.servlets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.external.servlets.entity.Client;
import ua.external.servlets.pool.ConnectionPool;
import ua.external.servlets.service.ServiceException;

import java.io.FileReader;
import java.util.Optional;

import static org.junit.Assert.*;

public class ClientServiceTest {
    private final static Logger logger = LogManager.getLogger();
    ClientService clientService;

    private static final Client client = new Client();
    private static final Long ID = new Long(4);

    @Before
    public void setUp() throws Exception {
        logger.info("Start test in clientServiceTest");
        clientService = Mockito.spy(ClientService.class);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("End test in clientServiceTest");
        clientService = null;
    }

    @Test
    public void createClient() throws ServiceException {
        assertFalse(clientService.createClient(client));
    }

    @Test
    public void updateClient() throws ServiceException {
        assertFalse(clientService.updateClient(client));
    }

    @Test
    public void findClientById() throws ServiceException {
        assertEquals(client, clientService.findClientById(ID));
    }
}