package ua.external.servlets.command.impl.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.builder.ClientBuilder;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.*;
import ua.external.servlets.service.*;
import ua.external.servlets.service.impl.*;
import ua.external.servlets.util.cоnst.JspConst;
import ua.external.servlets.util.page.Page;
import ua.external.servlets.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_CLIENT;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_NAME;

/**
 * Gets client information.
 * Validates this values, if input data is not valid,
 * returns router to the same page with message about invalid values.
 * Otherwise, create client and returns router to the same page.
 */
public class CreateClientInfoCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(CreateClientInfoCommand.class);
    private UserService userService = new UserService();
    private ClientService clientService = new ClientService();
    private GenderService genderService = new GenderService();
    private ActivityService activityService = new ActivityService();
    private NutritionGoalService nutritionGoalService = new NutritionGoalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        String name = request.getParameter(PARAM_NAME);
        if (name == null || !DataValidator.validateName(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute(JspConst.INVALID_NAME, true);
            return new CommandResult(Page.CLIENT_INFO, true);
        }

        Integer age = Integer.parseInt(request.getParameter(PARAM_AGE));
        if (age == null || !DataValidator.validateAge(age)) {
            log.info("invalid age format was received:" + age);
            request.setAttribute(JspConst.INVALID_AGE, true);
            return new CommandResult(Page.CLIENT_INFO, true);
        }

        Double height = Double.parseDouble(request.getParameter(PARAM_HEIGHT));
        if (height == null || !DataValidator.validateHeight(height)) {
            log.info("invalid height format was received:" + height);
            request.setAttribute(INVALID_HEIGHT, true);
            return new CommandResult(Page.CLIENT_INFO, true);
        }

        Double weight = Double.parseDouble(request.getParameter(PARAM_WEIGHT));
        if (weight == null || !DataValidator.validateWeight(weight)) {
            log.info("invalid weight format was received:" + weight);
            request.setAttribute(INVALID_WEIGHT, true);
            return new CommandResult(Page.CLIENT_INFO, true);
        }

        Client client;
        try {
            client = buildClient(request);
            if (clientService.createClient(client)) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(PARAM_USER);
                user.setClientId(client.getId());
                if (userService.updateUser(user)) {
                    session.setAttribute(SESSION_CLIENT, client);
                    session.setAttribute(SESSION_NAME, client.getName());
                    log.info("create client information for user with id=" + user.getId());
                    page = Page.WELCOME_PAGE;
                } else {
                    request.setAttribute(WRONG_DATA, true);
                    return new CommandResult(Page.CLIENT_INFO_PAGE);
                }
            } else {
                request.setAttribute(WRONG_DATA, true);
                return new CommandResult(Page.CLIENT_INFO_PAGE);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_INFO;
        }

        return new CommandResult(page, true);
    }

    private Client buildClient(HttpServletRequest request) throws ServiceException {
        String name = request.getParameter(PARAM_NAME);
        Integer age = Integer.parseInt(request.getParameter(PARAM_AGE));
        Long gender_id = Long.parseLong(request.getParameter(PARAM_GENDER));


        Double height = Double.parseDouble(request.getParameter(PARAM_HEIGHT));
        Double weight = Double.parseDouble(request.getParameter(PARAM_WEIGHT));
        Long activity_id = Long.parseLong(request.getParameter(PARAM_ACTIVITY));

        Long nutritionGoal_id = Long.parseLong(request.getParameter(PARAM_NUTRITION_GOAL));

        Gender gender = genderService.findGenderById(gender_id).get();
        Activity activity = activityService.findActivityById(activity_id).get();
        NutritionGoal nutritionGoal = nutritionGoalService.findNutritionGoalById(nutritionGoal_id).get();

        return new ClientBuilder()
                .setName(name)
                .setAge(age)
                .setGender(gender)
                .setHeight(height)
                .setWeight(weight)
                .setActivity(activity)
                .setNutritionGoal(nutritionGoal)
                .createClient();
    }



}
