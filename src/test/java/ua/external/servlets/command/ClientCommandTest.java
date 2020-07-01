package ua.external.servlets.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.external.servlets.command.impl.LoginCommand;
import ua.external.servlets.command.impl.client.ClientUpdateCommand;
import ua.external.servlets.command.impl.client.CreateClientInfoCommand;
import ua.external.servlets.entity.*;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.ActivityService;
import ua.external.servlets.service.impl.GenderService;
import ua.external.servlets.service.impl.NutritionGoalService;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_CLIENT;

public class ClientCommandTest {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    UserService userService;
    GenderService genderService;
    ActivityService activityService;
    NutritionGoalService nutritionGoalService;
    CreateClientInfoCommand createClientInfoCommand;
    ClientUpdateCommand clientUpdateCommand;
    Client testClient;

    @Before
    public void setUp(){
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);

        genderService = mock(GenderService.class);
        activityService = mock(ActivityService.class);
        nutritionGoalService = mock(NutritionGoalService.class);

        createClientInfoCommand = new CreateClientInfoCommand();
        clientUpdateCommand = new ClientUpdateCommand();

        testClient = new Client();
        testClient.setId(new Long(12));
    }

    @Test
    public void createClientInfoCommandExecute() throws ServiceException {
        when(request.getParameter(PARAM_NAME)).thenReturn("Yulia");
        when(request.getParameter(PARAM_AGE)).thenReturn("20");
        when(request.getParameter(PARAM_HEIGHT)).thenReturn("165");
        when(request.getParameter(PARAM_WEIGHT)).thenReturn("60");

        when(request.getSession()).thenReturn(session);

        when(request.getParameter(PARAM_GENDER)).thenReturn("2");
        when(request.getParameter(PARAM_ACTIVITY)).thenReturn("2");
        when(request.getParameter(PARAM_NUTRITION_GOAL)).thenReturn("2");

        when(session.getAttribute(PARAM_USER)).thenReturn(new User());

        when(genderService.findGenderById(anyLong())).thenReturn(Optional.of(new Gender()));
        when(activityService.findActivityById(anyLong())).thenReturn(Optional.of(new Activity()));
        when(nutritionGoalService.findNutritionGoalById(anyLong())).thenReturn(Optional.of(new NutritionGoal()));

        CommandResult result = createClientInfoCommand.execute(request, response);

        assertEquals(Page.WELCOME_PAGE, result.getPage());
    }

    @Test
    public void clientUpdateCommandExecute() throws ServiceException {
        when(request.getParameter(PARAM_ID)).thenReturn("12");
        when(request.getParameter(PARAM_NAME)).thenReturn("YuliaNew");
        when(request.getParameter(PARAM_AGE)).thenReturn("25");
        when(request.getParameter(PARAM_HEIGHT)).thenReturn("160");
        when(request.getParameter(PARAM_WEIGHT)).thenReturn("80");

        when(request.getSession()).thenReturn(session);

        when(request.getParameter(PARAM_GENDER)).thenReturn("2");
        when(request.getParameter(PARAM_ACTIVITY)).thenReturn("2");
        when(request.getParameter(PARAM_NUTRITION_GOAL)).thenReturn("2");

        when(genderService.findGenderById(anyLong())).thenReturn(Optional.of(new Gender()));
        when(activityService.findActivityById(anyLong())).thenReturn(Optional.of(new Activity()));
        when(nutritionGoalService.findNutritionGoalById(anyLong())).thenReturn(Optional.of(new NutritionGoal()));

        when(session.getAttribute(SESSION_CLIENT)).thenReturn(testClient);

        CommandResult result = clientUpdateCommand.execute(request, response);

        assertEquals(Page.WELCOME_PAGE, result.getPage());
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        session = null;
        userService = null;

        genderService = null;
        activityService = null;
        nutritionGoalService = null;

        createClientInfoCommand = null;
    }
}
