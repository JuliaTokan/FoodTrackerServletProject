package ua.external.servlets.controller.old;

import ua.external.servlets.builder.MealsBuilder;
import ua.external.servlets.entity.EatPeriod;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.EatPeriodService;
import ua.external.servlets.service.impl.MealsService;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

//@WebServlet(name = "MealsServlet", urlPatterns = {"/user/meals"})
public class MealsServlet extends HttpServlet {
    private ProductService productService;
    private MealsService mealsService;
    private EatPeriodService eatPeriodService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
        mealsService = new MealsService();
        eatPeriodService = new EatPeriodService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Product> products = new ArrayList<>();
        List<Meals> meals = new ArrayList<>();
        List<EatPeriod> eatPeriods = new ArrayList<>();

        try {
            products = productService.findAllProductsForUser(user.getId());
            meals = mealsService.getAllMealForUser(user.getId());
            eatPeriods = eatPeriodService.findAllEatPeriods();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        request.setAttribute("eatPeriods", eatPeriods);
        request.setAttribute("meals", getStructuresInfo(meals, eatPeriods));
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long product_id = Long.parseLong(request.getParameter("food"));
        Integer weight = Integer.parseInt(request.getParameter("weight"));
        Long eatPeriod_id = Long.parseLong(request.getParameter("eat_period"));

        EatPeriod eatPeriod = null;
        try {
            eatPeriod = eatPeriodService.findEatPeriodById(eatPeriod_id).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null)
            response.sendRedirect("/signup");

        Product product = null;
        try {
            Optional<Product> optionalProduct = productService.findProductById(product_id);
            product = optionalProduct.isPresent() ? optionalProduct.get() : null;
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Meals meals = new MealsBuilder()
                .setUserId(user.getId())
                .setProduct(product)
                .setWeight(weight)
                .setEatPeriod(eatPeriod)
                .setDate(new Timestamp(System.currentTimeMillis()))
                .createMeals();

        MealsService mealsService = new MealsService();
        try {
            mealsService.createMeals(meals);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/user/meals");
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
