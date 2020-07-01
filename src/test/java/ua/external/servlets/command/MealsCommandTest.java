package ua.external.servlets.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.command.impl.meals.AddMealsCommand;
import ua.external.servlets.command.impl.meals.DeleteMealsCommand;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.JspConst.PARAM_USER;

public class MealsCommandTest {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    AddMealsCommand addMealsCommand;
    DeleteMealsCommand deleteMealsCommand;

    @Before
    public void setUp(){
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);

        addMealsCommand = new AddMealsCommand();
        deleteMealsCommand = new DeleteMealsCommand();
    }

    @Test
    public void addMealsCommandExecute() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(PARAM_WEIGHT)).thenReturn("250");
        when(request.getParameter(PARAM_EAT_PERIOD)).thenReturn("3");
        when(request.getParameter(PARAM_FOOD)).thenReturn("24");
        when(session.getAttribute(PARAM_USER)).thenReturn(new UserBuilder().setId(new Long(12)).createUser());

        CommandResult result = addMealsCommand.execute(request, response);

        assertEquals(Page.MEALS, result.getPage());
    }

    @Test
    public void deleteMealsCommandExecute() {
        when(request.getParameterValues(PARAM_MEALS_DELETE)).thenReturn(new String[]{"26", "27"});

        CommandResult result = deleteMealsCommand.execute(request, response);

        assertEquals(Page.MEALS_PAGE, result.getPage());
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        session = null;
        addMealsCommand = null;
    }
}
