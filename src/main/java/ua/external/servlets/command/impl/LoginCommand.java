package ua.external.servlets.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.ClientService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.cоnst.JspConst;
import ua.external.servlets.util.password.PasswordHashGenerator;
import ua.external.servlets.util.page.Page;
import ua.external.servlets.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.*;

/**
 * Gets login and password values from the request.
 * Validates this values, if input data is not valid, or no such user is presented in the database (user is null),
 * returns router to the same page with message about incorrect login or password.
 * Otherwise, finds the user by this values and sets sessions attributes and
 * returns router to the welcome page.
 */
public class LoginCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LoginCommand.class);
    private UserService userService = new UserService();
    private ClientService clientService = new ClientService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String page;
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !DataValidator.validateLogin(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(JspConst.INVALID_LOGIN, true);
            request.setAttribute(JspConst.SIGNIN, true);
            return new CommandResult(Page.REGISTER_PAGE);
        }

        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !DataValidator.validatePassword(password)) {
            log.info("invalid password format was received:" + password);
            request.setAttribute(JspConst.INVALID_PASSWORD, true);
            request.setAttribute(JspConst.SIGNIN, true);
            return new CommandResult(Page.REGISTER_PAGE);
        }

        final HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("exist_user")) && (boolean)session.getAttribute("exist_user") == true) {
            log.info("user is already authorized");
            return new CommandResult(Page.WELCOME_PAGE, true);
        } else {
            PasswordHashGenerator passwordHashGenerator = new PasswordHashGenerator();
            try {
                Optional<User> userOptional = userService.findUserByLogin(login);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (user.getPassword().equals(passwordHashGenerator.hash(password))) {
                        session.setAttribute(SESSION_EXIST_USER, true);
                        session.setAttribute(SESSION_USER, user);
                        log.info("user with id = " + user.getId() + " log in");

                        if(user.getClient_id() != 0){
                            Optional<Client> optionalClient = clientService.findClientById(user.getClient_id());
                            if(optionalClient.isPresent()){
                                Client client = optionalClient.get();
                                session.setAttribute(SESSION_CLIENT, client);
                                session.setAttribute(SESSION_NAME, client.getName());
                                page = Page.WELCOME_PAGE;
                            }
                            else page = Page.CLIENT_INFO;
                        }
                        else page = Page.CLIENT_INFO;
                    } else {
                        log.info("user entered incorrect pass");
                        request.setAttribute(JspConst.SIGNIN, true);
                        request.setAttribute(JspConst.WRONG_DATA, true);
                        return new CommandResult(Page.REGISTER_PAGE);
                    }
                } else {
                    request.setAttribute(JspConst.SIGNIN, true);
                    request.setAttribute(JspConst.WRONG_DATA, true);
                    return new CommandResult(Page.REGISTER_PAGE);
                }
            } catch (ServiceException e) {
                log.error("Problem with service occurred!", e);
                page = Page.LOGIN_PAGE;
            }
        }
        return new CommandResult(page, true);
    }
}
