package ua.external.servlets.controller.old;

import ua.external.servlets.builder.ClientBuilder;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.entity.Client;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.*;
import ua.external.servlets.service.impl.ActivityService;
import ua.external.servlets.service.impl.ClientService;
import ua.external.servlets.service.impl.GenderService;
import ua.external.servlets.service.impl.NutritionGoalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "ClientUpdateServlet", urlPatterns = {"/client/update"})
public class ClientUpdateServlet extends HttpServlet {
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

        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");

        Client newClient = new ClientBuilder(client)
                .setName(name)
                .setAge(age)
                .setGender(gender)
                .setHeight(height)
                .setWeight(weight)
                .setActivity(activity)
                .setNutritionGoal(nutritionGoal)
                .createClient();

        boolean updated = false;
        try {
            updated = clientService.updateClient(newClient);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if(updated){
            session.setAttribute("client", newClient);
            session.setAttribute("name", newClient.getName());
        }

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");

        if(client == null) {
            response.sendRedirect("/client/info");
            return;
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
                e.printStackTrace();
            }

            request.setAttribute("genders", genders);
            request.setAttribute("activities", activities);
            request.setAttribute("nutritionGoals", nutritionGoals);

            request.setAttribute("cl_name", client.getName());
            request.setAttribute("cl_age", client.getAge());
            request.setAttribute("cl_gender", client.getGender().getId());
            request.setAttribute("cl_height", client.getHeight());
            request.setAttribute("cl_weight", client.getWeight());
            request.setAttribute("cl_activity", client.getActivity().getId());
            request.setAttribute("cl_nutr_goal", client.getNutritionGoal().getId());
        }

        getServletContext().getRequestDispatcher("/client_edit.jsp").forward(request, response);
    }
}
