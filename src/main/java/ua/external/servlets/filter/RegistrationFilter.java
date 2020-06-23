package ua.external.servlets.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.page.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_EXIST_USER;

/**
 * The {@code RegistrationFilter} class
 * is an implementation of {@code Filter} interface.
 * Checks user data exist.
 */
@WebFilter(filterName = "RegistrationFilter", urlPatterns = {"/sign/up"})
public class RegistrationFilter implements Filter {
    private static Logger log = LogManager.getLogger(RegistrationFilter.class);
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        userService = new UserService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String login = request.getParameter(PARAM_LOGIN);
        //final String password = request.getParameter(PARAM_PASSWORD);

        final HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute(SESSION_EXIST_USER)) && (boolean) session.getAttribute(SESSION_EXIST_USER) == true) {
            //user already login
            response.sendRedirect(Page.WELCOME_PAGE);
        } else {
            try {
                Optional<User> userOptional = userService.findUserByLogin(login);
                if (userOptional.isPresent()) {
                    log.info("User with email " + login + " exist.");
                    request.setAttribute(INVALID_USER, true);
                    //request.setAttribute(PARAM_LOGIN, login);
                    //request.setAttribute(PARAM_PASSWORD, password);

                    request.getRequestDispatcher(Page.REGISTER_PAGE).forward(request, response);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
