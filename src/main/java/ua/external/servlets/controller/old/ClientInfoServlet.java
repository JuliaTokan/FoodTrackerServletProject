package ua.external.servlets.controller.old;

import ua.external.servlets.builder.ClientBuilder;
import ua.external.servlets.entity.*;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.*;
import ua.external.servlets.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "ClientInfoServlet", urlPatterns = {"/client/info"})
public class ClientInfoServlet extends HttpServlet {
    private ClientService clientService;
    private GenderService genderService;
    private ActivityService activityService;
    private NutritionGoalService nutritionGoalService;

    @Override
    public void init() throws ServletException {
        super.init();
        clientService = new ClientService();
        genderService = new GenderService();
        activityService = new ActivityService();
        nutritionGoalService = new NutritionGoalService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long gender_id = Long.parseLong(request.getParameter("gender"));


        Double height = Double.parseDouble(request.getParameter("height"));
        Double weight = Double.parseDouble(request.getParameter("weight"));
        Long activity_id = Long.parseLong(request.getParameter("activity"));

        Long nutritionGoal_id = Long.parseLong(request.getParameter("nutr_goal"));

        Gender gender = null;
        Activity activity = null;
        NutritionGoal nutritionGoal = null;
        try {
            gender = genderService.findGenderById(gender_id).get();
            activity = activityService.findActivityById(activity_id).get();
            nutritionGoal = nutritionGoalService.findNutritionGoalById(nutritionGoal_id).get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        Client client = new ClientBuilder()
                .setName(name)
                .setAge(age)
                .setGender(gender)
                .setHeight(height)
                .setWeight(weight)
                .setActivity(activity)
                .setNutritionGoal(nutritionGoal)
                .createClient();

        boolean created = false;
        try {
            created = clientService.createClient(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        UserService userService = new UserService();
        if(created){
            User user = (User) session.getAttribute("user");
            user.setClient_id(client.getId());
            try {
                userService.updateUser(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            session.setAttribute("client", client);
            session.setAttribute("name", client.getName());
        }
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.setAttribute("genders", genders);
        request.setAttribute("activities", activities);
        request.setAttribute("nutritionGoals", nutritionGoals);

        getServletContext().getRequestDispatcher("/client_info_registration.jsp").forward(request, response);
    }
}
