package ua.external.servlets.controller.old;

import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "AdminDeleteProductServlet", urlPatterns = {"/admin/product/delete"})
public class AdminDeleteProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            productService.deleteProductById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getHeader("referer"));
    }
}
