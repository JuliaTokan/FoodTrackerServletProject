package ua.external.servlets.controller.old;

import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserRoleService;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.password.PasswordHashGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(name = "RegistrationServlet", urlPatterns = {"/signup"})
public class RegistrationServlet extends HttpServlet {
    private UserService userService;
    private UserRoleService userRoleService;
    private Long userRoleId = new Long(1);

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
        userRoleService = new UserRoleService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("pass");

        UserRole userRole = null;
        try {
            userRole = userRoleService.findUserRoleById(userRoleId).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        PasswordHashGenerator passwordHashGenerator = new PasswordHashGenerator();
        User user = new UserBuilder()
                .setLogin(login)
                .setPassword(passwordHashGenerator.hash(password))
                .setRole(userRole)
                .createUser();

        boolean created = false;
        try {
            created = userService.createUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(created){
            HttpSession session = request.getSession();
            session.setAttribute("exist_user", true);
            session.setAttribute("user", user);
            response.sendRedirect("/client/info");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/first_registration.jsp").forward(request, response);
    }
}
