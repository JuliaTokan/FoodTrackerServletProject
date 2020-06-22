package ua.external.servlets.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.command.impl.product.AddProductCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.external.servlets.util.cоnst.JspConst.*;

public class ProductCommand {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    AddProductCommand addProductCommand;

    @Before
    public void setUp(){
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        addProductCommand = new AddProductCommand();
    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(PARAM_NAME)).thenReturn("TestProduct");
        when(request.getParameter(PARAM_CALORIES)).thenReturn("356");
        when(request.getParameter(PARAM_PROTEIN)).thenReturn("3.6");
        when(request.getParameter(PARAM_FATS)).thenReturn("4.8");
        when(request.getParameter(PARAM_CARBOHYDRATES)).thenReturn("2.4");
        when(session.getAttribute(PARAM_USER)).thenReturn(new UserBuilder().setId(new Long(12)).createUser());

        CommandResult result = addProductCommand.execute(request, response);

        assertNotNull(result);
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        session = null;
        addProductCommand = null;
    }
}
