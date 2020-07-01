package ua.external.servlets.filter;

import ua.external.servlets.entity.User;
import ua.external.servlets.util.cоnst.SessionConst;
import ua.external.servlets.util.page.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * The {@code AdminFilter} class
 * is an implementation of {@code Filter} interface.
 * Checks user role.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if (nonNull(session.getAttribute(SessionConst.SESSION_EXIST_USER)) &&
                (boolean) session.getAttribute(SessionConst.SESSION_EXIST_USER)) {
            User user = (User) session.getAttribute(SessionConst.SESSION_USER);

            if (!user.getRole().getRole().equals("ADMIN")) {
                response.sendRedirect(Page.WELCOME_PAGE);
            } else filterChain.doFilter(servletRequest, servletResponse);
        } else response.sendRedirect(Page.WELCOME_PAGE);
    }

    @Override
    public void destroy() {

    }
}
