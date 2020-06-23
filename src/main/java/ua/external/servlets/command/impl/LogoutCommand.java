package ua.external.servlets.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.User;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.external.servlets.util.cоnst.SessionConst.*;

/**
 * Invalidates user session.
 */
public class LogoutCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession();
        Long userId = ((User) session.getAttribute(SESSION_USER)).getId();
        session.removeAttribute(SESSION_EXIST_USER);
        session.removeAttribute(SESSION_NAME);
        session.removeAttribute(SESSION_USER);
        session.removeAttribute(SESSION_CLIENT);

        log.info("user with id = " + userId + " log out");
        return new CommandResult(Page.WELCOME_PAGE, true);
    }
}
