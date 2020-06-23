package ua.external.servlets.command.impl.meals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.builder.MealsBuilder;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.*;
import ua.external.servlets.mail.SendEmail;
import ua.external.servlets.service.impl.*;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.page.Page;
import ua.external.servlets.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import static ua.external.servlets.util.cоnst.JspConst.*;

/**
 * Gets meals information.
 * Validates this values, if input data is not valid,
 * returns router to the same page with message about invalid values.
 * Otherwise, create meals and returns router to the same page.
 */
public class AddMealsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddMealsCommand.class);
    private ProductService productService = new ProductService();
    private MealsService mealsService = new MealsService();
    private EatPeriodService eatPeriodService = new EatPeriodService();
    private UserService userService = new UserService();
    private ClientService clientService = new ClientService();
    private SendEmail sendEmail = new SendEmail();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        Integer weight = Integer.parseInt(request.getParameter(PARAM_WEIGHT));
        if (weight == null || !DataValidator.validateProductWeight(weight)) {
            log.info("invalid weight format was received:" + weight);
            request.setAttribute(INVALID_WEIGHT, true);
            return new CommandResult(Page.MEALS, true);
        }
        Meals meals;
        try {
            meals = buildMeals(request);
            if (mealsService.createMeals(meals)) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(PARAM_USER);
                log.info("create meals for user with id=" + user.getId());
                page = Page.MEALS;
                checkCaloriesLimit(user);
            } else {
                request.setAttribute(WRONG_DATA, true);
                return new CommandResult(Page.MEALS_PAGE);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.MEALS;
        }

        return new CommandResult(page, true);
    }

    private Meals buildMeals(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(PARAM_USER);

        Long product_id = Long.parseLong(request.getParameter(PARAM_FOOD));
        Integer weight = Integer.parseInt(request.getParameter(PARAM_WEIGHT));
        Long eatPeriod_id = Long.parseLong(request.getParameter(PARAM_EAT_PERIOD));

        EatPeriod eatPeriod = eatPeriodService.findEatPeriodById(eatPeriod_id).get();
        Product product = productService.findProductById(product_id).get();

        return new MealsBuilder()
                .setUserId(user.getId())
                .setProduct(product)
                .setWeight(weight)
                .setEatPeriod(eatPeriod)
                .setDate(new Timestamp(System.currentTimeMillis()))
                .createMeals();
    }

    private void checkCaloriesLimit(User user) {
        Integer calories = 0;
        try {
            calories = userService.countCalories(user);
        } catch (ServiceException e) {
            log.info("Cannot check calories for user with id=" + user.getId());
        }

        if (user.getClient_id() != null && user.getClient_id() != 0) {
            try {
                Client client = clientService.findClientById(user.getClient_id()).get();
                if (client.getCalories() < calories) {
                    sendEmail.sendWarningLetter(user.getLogin());
                }
            } catch (ServiceException e) {
                log.error("Problem with service occurred!", e);
            }
        }
    }
}
