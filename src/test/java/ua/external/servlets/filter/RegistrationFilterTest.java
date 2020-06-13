package ua.external.servlets.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.external.servlets.util.cоnst.JspConst.PARAM_LOGIN;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_EXIST_USER;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationFilterTest {
    RegistrationFilter filter = new RegistrationFilter();
    private static final String LOGIN_PARAM = "yulia.tokan.11@gmail.com";
    private static final String PASSWORD_PARAM = "qwe12345";
    private static final Long ROLE_ID = new Long(1);

    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    public void doFilter_admin_acceptsAdmin() throws IOException, ServletException {
        when(session.getAttribute(SESSION_EXIST_USER)).thenReturn(true);
        when(request.getParameter(PARAM_LOGIN)).thenReturn(anyString());
        when(request.getSession()).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }
}