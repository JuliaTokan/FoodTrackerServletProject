package ua.external.servlets.filter;

import ua.external.servlets.entity.User;
import ua.external.servlets.util.page.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_EXIST_USER;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_USER;

/**
 * The {@code UserFilter} class
 * is an implementation of {@code Filter} interface.
 * Checks user login and command if it is accessible for him.
 */
@WebFilter(filterName = "UserFilter", urlPatterns = {"/client/*", "/user/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if (nonNull(session.getAttribute(SESSION_EXIST_USER)) &&
                (boolean) session.getAttribute(SESSION_EXIST_USER)) {
            User user = (User) session.getAttribute(SESSION_USER);
            if (user == null) {
                response.sendRedirect(Page.LOGIN_PAGE);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            response.sendRedirect(Page.LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
}
