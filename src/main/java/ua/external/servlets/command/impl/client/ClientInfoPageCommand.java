package ua.external.servlets.command.impl.client;

import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.impl.ActivityService;
import ua.external.servlets.service.impl.GenderService;
import ua.external.servlets.service.impl.NutritionGoalService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.external.servlets.util.cоnst.JspConst.*;

/**
 * Get client sign up page
 */
public class ClientInfoPageCommand implements ActionCommand {
    private GenderService genderService = new GenderService();
    private ActivityService activityService = new ActivityService();
    private NutritionGoalService nutritionGoalService = new NutritionGoalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Gender> genders = new ArrayList<>();
        List<Activity> activities = new ArrayList<>();
        List<NutritionGoal> nutritionGoals = new ArrayList<>();

        try {
            genders = genderService.findAllGenders();
            activities = activityService.findAllActivities();
            nutritionGoals = nutritionGoalService.findAllNutritionGoals();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(PARAM_GENDERS, genders);
        request.setAttribute(PARAM_ACTIVITIES, activities);
        request.setAttribute(PARAM_NUTR_GOALS, nutritionGoals);

        return new CommandResult(Page.CLIENT_INFO_PAGE);
    }
}
