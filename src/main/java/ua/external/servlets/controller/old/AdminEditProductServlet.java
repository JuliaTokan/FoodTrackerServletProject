package ua.external.servlets.controller.old;

import ua.external.servlets.builder.ProductBuilder;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(name = "AdminEditProductServlet", urlPatterns = {"/admin/product/edit"})
public class AdminEditProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        Double protein = Double.parseDouble(request.getParameter("protein"));
        Double fats = Double.parseDouble(request.getParameter("fats"));
        Double carbohydrates = Double.parseDouble(request.getParameter("carbohydrates"));
        Boolean isPublic = request.getParameter("public")==null?false:request.getParameter("public").equals("on")?true:false;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Product product = new ProductBuilder()
                .setId(id)
                .setUserId(user.getId())
                .setName(name)
                .setCalories(calories)
                .setProtein(protein)
                .setFats(fats)
                .setCarbohydrates(carbohydrates)
                .setCommon(isPublic)
                .createProduct();

        try {
            productService.updateProduct(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getHeader("referer"));
    }
}
