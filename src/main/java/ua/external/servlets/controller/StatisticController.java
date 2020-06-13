package ua.external.servlets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.service.impl.MealsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet(name = "StatController", urlPatterns = {"/statistic"})
public class StatisticController extends HttpServlet {

    private MealsService mealsService = new MealsService();
    private static final int WEEK = 7;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*try {
            LocalDate cur_date = LocalDate.now();
            HashMap<String, Integer> weekInfo = new LinkedHashMap<>();

            for(int i = 0; i<WEEK; i++){
                LocalDate date = cur_date.minusDays(i);
                List<Meals> meals = mealsService.getAllMealForUserByDate(new Long(17), date);
                Integer numOfCalories = meals.stream().mapToInt(x->x.getProduct().getCalories()*x.getWeight()/100).sum();
                System.out.println(date.toString()+" - "+numOfCalories);
                weekInfo.put(date.toString(), numOfCalories);
            }
            ObjectMapper mapper = new ObjectMapper();
            request.setAttribute("weekInfo", mapper.writeValueAsString(weekInfo));

        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        getServletContext().getRequestDispatcher("/statistic.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
