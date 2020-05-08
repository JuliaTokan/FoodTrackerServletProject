package ua.external.servlets.command.impl.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.*;
import ua.external.servlets.service.impl.ActivityService;
import ua.external.servlets.service.impl.GenderService;
import ua.external.servlets.service.impl.NutritionGoalService;
import ua.external.servlets.util.cоnst.JspConst;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.*;

public class ClientUpdatePageCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ClientUpdatePageCommand.class);
    private GenderService genderService = new GenderService();
    private ActivityService activityService = new ActivityService();
    private NutritionGoalService nutritionGoalService = new NutritionGoalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(SESSION_CLIENT);

        if(client == null) {
            log.error("Try edit nonexistent client. Redirect to create client information");
            return new CommandResult(Page.CLIENT_INFO, true);
        }
        else {
            List<Gender> genders = new ArrayList<>();
            List<Activity> activities = new ArrayList<>();
            List<NutritionGoal> nutritionGoals = new ArrayList<>();

            try {
                genders = genderService.findAllGenders();
                activities = activityService.findAllActivities();
                nutritionGoals = nutritionGoalService.findAllNutritionGoals();
            } catch (ServiceException e) {
                log.error("Problem with service occurred!", e);
                return new CommandResult(Page.CLIENT_EDIT, true);
            }

            request.setAttribute(PARAM_GENDERS, genders);
            request.setAttribute(PARAM_ACTIVITIES, activities);
            request.setAttribute(PARAM_NUTR_GOALS, nutritionGoals);

            request.setAttribute("cl_name", client.getName());
            request.setAttribute("cl_age", client.getAge());
            request.setAttribute("cl_gender", client.getGender().getId());
            request.setAttribute("cl_height", client.getHeight());
            request.setAttribute("cl_weight", client.getWeight());
            request.setAttribute("cl_activity", client.getActivity().getId());
            request.setAttribute("cl_nutr_goal", client.getNutritionGoal().getId());
            return new CommandResult(Page.CLIENT_EDIT_PAGE);
        }
    }
}
