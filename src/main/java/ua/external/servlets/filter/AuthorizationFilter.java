package ua.external.servlets.filter;

import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.ClientService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.password.PasswordHashGenerator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.nonNull;

//@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/sign/in"})
public class AuthorizationFilter implements Filter {
    UserService userService;
    ClientService clientService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserService();
        clientService = new ClientService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("email");
        final String password = request.getParameter("pass");

        final HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("exist_user")) && (boolean)session.getAttribute("exist_user") == true) {
            //user уже авторизирован
            response.sendRedirect("/");
        } else {
            PasswordHashGenerator passwordHashGenerator = new PasswordHashGenerator();
            try {
                Optional<User> userOptional = userService.findUserByLogin(login);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (user.getPassword().equals(passwordHashGenerator.hash(password))) {
                        session.setAttribute("exist_user", true);
                        session.setAttribute("user", user);
                        if(user.getClient_id() != 0){
                            Optional<Client> optionalClient = clientService.findClientById(user.getClient_id());
                            if(optionalClient.isPresent()){
                                Client client = optionalClient.get();
                                session.setAttribute("client", client);
                                session.setAttribute("name", client.getName());
                                response.sendRedirect("/");
                            }
                            else response.sendRedirect("/client/info");
                        }
                        else response.sendRedirect("/client/info");
                    } else {
                        //incorrect pass
                    }
                } else {
                    //user not found
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
