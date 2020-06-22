package ua.external.servlets.command;

import org.junit.*;
import ua.external.servlets.command.impl.RegisterCommand;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static ua.external.servlets.util.cоnst.JspConst.PARAM_LOGIN;
import static ua.external.servlets.util.cоnst.JspConst.PARAM_PASSWORD;

public class RegisterCommandTest {
    HttpServletResponse response;
    HttpServletRequest request;
    UserService userService;
    RegisterCommand registerCommand;


    @Before
    public void setUp(){
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        userService = mock(UserService.class);
        registerCommand = new RegisterCommand();
    }

    @Test
    public void execute() throws ServiceException {
        when(request.getParameter(PARAM_LOGIN)).thenReturn("user.test@gmail.com");
        when(request.getParameter(PARAM_PASSWORD)).thenReturn("qwe12345");
        when(userService.createUser(any(User.class))).thenReturn(true);

        CommandResult result = registerCommand.execute(request, response);

        assertEquals(Page.CLIENT_INFO, result.getPage());
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        userService = null;
        registerCommand = null;
    }
}
