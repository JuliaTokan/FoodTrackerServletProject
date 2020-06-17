package ua.external.servlets.command.impl.meals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.EatPeriod;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.EatPeriodService;
import ua.external.servlets.service.impl.MealsService;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.external.servlets.util.cоnst.JspConst.*;
import static ua.external.servlets.util.cоnst.SessionConst.*;

/**
 * Get meals page.
 */
public class MealsPageCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(MealsPageCommand.class);
    private ProductService productService = new ProductService();
    private MealsService mealsService = new MealsService();
    private EatPeriodService eatPeriodService = new EatPeriodService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);

        List<Product> products = new ArrayList<>();
        List<Meals> meals = new ArrayList<>();
        List<EatPeriod> eatPeriods = new ArrayList<>();

        try {
            products = productService.findAllProductsForUser(user.getId());
            meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());//getAllMealForUser(user.getId());
            eatPeriods = eatPeriodService.findAllEatPeriods();
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            return new CommandResult(Page.MEALS, true);
        }
        request.setAttribute(PARAM_PRODUCTS, products);
        request.setAttribute(PARAM_EAT_PERIODS, eatPeriods);
        request.setAttribute(PARAM_MEALS, getStructuresInfo(meals, eatPeriods));

        return new CommandResult(Page.MEALS_PAGE);
    }

    private Map<EatPeriod, List<Meals>> getStructuresInfo(List<Meals> meals, List<EatPeriod> eatPeriods) {
        Map<EatPeriod, List<Meals>> structMeals = new LinkedHashMap<>();

        for (EatPeriod eatPeriod : eatPeriods) {
            List<Meals> mealsByTime = meals.stream().filter(x -> x.getEatPeriod().getId() == eatPeriod.getId()).collect(Collectors.toList());
            structMeals.put(eatPeriod, mealsByTime);
        }
        return structMeals;
    }
}
