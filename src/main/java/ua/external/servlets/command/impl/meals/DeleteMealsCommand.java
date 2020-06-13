package ua.external.servlets.command.impl.meals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.service.impl.MealsService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.external.servlets.util.cоnst.JspConst.PARAM_MEALS_DELETE;

/**
 * Remove meals by array of ids.
 * Returns router to the same page.
 */
public class DeleteMealsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(DeleteMealsCommand.class);
    private MealsService mealsService = new MealsService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String[] textIds = request.getParameterValues(PARAM_MEALS_DELETE);
        if (textIds != null) {
            for (String textId : textIds) {
                Long id = Long.parseLong(textId);
                try {
                    mealsService.deleteMealsById(id);
                    log.info("Delete meals with id=" + id);
                } catch (ServiceException e) {
                    log.error("Problem with service occurred!", e);
                    return new CommandResult(Page.MEALS, true);
                }
            }
        }
        return new CommandResult(Page.MEALS_PAGE);
    }
}
