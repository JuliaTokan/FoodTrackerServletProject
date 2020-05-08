package ua.external.servlets.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.builder.UserBuilder;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.User;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.mail.SendEmail;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserRoleService;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.password.PasswordHashGenerator;
import ua.external.servlets.util.page.Page;
import ua.external.servlets.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_EXIST_USER;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_USER;

public class RegisterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);
    private PasswordHashGenerator passwordHashGenerator = new PasswordHashGenerator();
    private UserService userService = new UserService();
    private UserRoleService userRoleService = new UserRoleService();
    private SendEmail sendEmail = new SendEmail();
    private Long userRoleId = new Long(1);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String login = request.getParameter(PARAM_LOGIN);

        if (login==null || !DataValidator.validateLogin(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, true);
            return new CommandResult(Page.REGISTER_PAGE);
        }

        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !DataValidator.validatePassword(password)){
            log.info("invalid password format was received:" + password);
            request.setAttribute(INVALID_PASSWORD, true);
            return new CommandResult(Page.REGISTER_PAGE);
        }

        User user;
        try {
            user = buildUser(request);
            if (userService.createUser(user)) {
                HttpSession session = request.getSession();
                session.setAttribute(SESSION_EXIST_USER, true);
                session.setAttribute(SESSION_USER, user);
                log.info("user with login = " + login + " was registered. Activation Link was sent.");
                sendEmail.sendWelcomeLetter(login);
                page = Page.CLIENT_INFO;
            } else {
                request.setAttribute(WRONG_DATA, true);
                return new CommandResult(Page.REGISTER_PAGE);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.REGISTER_PAGE;
        }
        return new CommandResult(page, true);
    }

    private User buildUser(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        UserRole userRole = userRoleService.findUserRoleById(userRoleId).get();
        return new UserBuilder()
                .setLogin(login)
                .setPassword(passwordHashGenerator.hash(password))
                .setRole(userRole)
                .createUser();
    }
}
