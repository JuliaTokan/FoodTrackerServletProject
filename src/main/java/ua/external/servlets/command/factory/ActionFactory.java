package ua.external.servlets.command.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandEnum;
import ua.external.servlets.command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static Logger log = LogManager.getLogger(ActionFactory.class);

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String  action = request.getRequestURI().replace("/","");
        action += request.getContentType()==null?"get":"post";
        if (action == null || action.isEmpty()) {
            return currentCommand;
        }
        try {
            currentCommand = CommandEnum.getCurrentCommand(action);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", true);
            log.info("Wrong action!");
        }
        return currentCommand;
    }
}
