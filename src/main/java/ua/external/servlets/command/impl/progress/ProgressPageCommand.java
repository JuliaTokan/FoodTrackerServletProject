package ua.external.servlets.command.impl.progress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.ClientService;
import ua.external.servlets.service.impl.MealsService;
import ua.external.servlets.service.impl.UserService;
import ua.external.servlets.util.nutrition.NutritionCalculator;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.SESSION_USER;

/**
 * Get progress page.
 */
public class ProgressPageCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ProgressPageCommand.class);
    private UserService userService = new UserService();
    private ClientService clientService = new ClientService();
    private MealsService mealsService = new MealsService();
    private static final int WEEK = 7;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);

        try {
            Integer currentCalories = userService.countCalories(user);
            Integer currentProtein = userService.countProtein(user);
            Integer currentFats = userService.countFats(user);
            Integer currentCarbohydrates = userService.countCarbohydrates(user);
            Integer maxCalories = 0;
            Integer maxProtein = 0;
            Integer maxFats = 0;
            Integer maxCarbohydrates = 0;

            if (user.getClient_id() == null || user.getClient_id() == 0) {
                maxCalories = NutritionCalculator.AVR_CALORIES;
                maxProtein = NutritionCalculator.calculateProtein(maxCalories).intValue();
                maxFats = NutritionCalculator.calculateFats(maxCalories).intValue();
                maxCarbohydrates = NutritionCalculator.calculateCarbohydrates(maxCalories).intValue();
            } else {
                Client client = clientService.findClientById(user.getClient_id()).get();
                maxCalories = client.getCalories();
                maxProtein = client.getProtein().intValue();
                maxFats = client.getFats().intValue();
                maxCarbohydrates = client.getCarbohydrates().intValue();
            }

            try {
                LocalDate cur_date = LocalDate.now();
                HashMap<String, Integer> weekInfo = new LinkedHashMap<>();

                for (int i = 0; i < WEEK; i++) {
                    LocalDate date = cur_date.minusDays(i);
                    List<Meals> meals = mealsService.getAllMealForUserByDate(user.getId(), date);
                    Integer numOfCalories = meals.stream()
                            .mapToInt(x -> x.getProduct().getCalories() * x.getWeight() / 100).sum();
                    weekInfo.put(date.toString(), numOfCalories);
                }
                ObjectMapper mapper = new ObjectMapper();
                request.setAttribute("weekInfo", mapper.writeValueAsString(weekInfo));

            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            request.setAttribute(CURRENT_CALORIES, currentCalories);
            request.setAttribute(MAX_CALORIES, maxCalories);
            request.setAttribute(CURRENT_PERCENTAGE, currentCalories * 100 / maxCalories);
            request.setAttribute(CURRENT_PROTEIN, currentProtein);
            request.setAttribute(MAX_PROTEIN, maxProtein);
            request.setAttribute(CURRENT_FATS, currentFats);
            request.setAttribute(MAX_FATS, maxFats);
            request.setAttribute(CURRENT_CARBOHYDRATES, currentCarbohydrates);
            request.setAttribute(MAX_CARBOHYDRATES, maxCarbohydrates);
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            return new CommandResult(request.getHeader(CURRENT_PAGE), true);
        }
        return new CommandResult(Page.USER_PROGRESS_PAGE);
    }
}
