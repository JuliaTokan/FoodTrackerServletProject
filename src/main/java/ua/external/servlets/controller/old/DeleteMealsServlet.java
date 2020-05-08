package ua.external.servlets.controller.old;

import ua.external.servlets.service.impl.MealsService;
import ua.external.servlets.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "DeleteMealsServlet", urlPatterns = {"/user/meals/delete"})
public class DeleteMealsServlet extends HttpServlet {
    private MealsService mealsService;

    @Override
    public void init() throws ServletException {
        super.init();
        mealsService = new MealsService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] textIds = request.getParameterValues("toDelete[]");
        if(textIds!=null) {
            for (String textId : textIds) {
                Long id = Long.parseLong(textId);
                try {
                    mealsService.deleteMealsById(id);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
