package ua.external.servlets.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.external.servlets.command.factory.ActionFactory;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActionFactoryTest {
    HttpServletRequest request;

    @Before
    public void setUp(){
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute(){
        when(request.getRequestURI()).thenReturn("http://localhost:8080/sign/up");
        ActionFactory actionFactory = new ActionFactory();
        ActionCommand actionCommand = actionFactory.defineCommand(request);
        assertEquals(CommandType.SIGNUPGET, actionCommand);
    }

    @After
    public void tearDown() throws Exception {
        request = null;
    }
}
