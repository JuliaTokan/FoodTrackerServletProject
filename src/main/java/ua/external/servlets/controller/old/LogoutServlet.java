package ua.external.servlets.controller.old;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final HttpSession session = httpServletRequest.getSession();
        //session.setAttribute("exist_user", false);
        session.removeAttribute("exist_user");
        session.removeAttribute("name");
        session.removeAttribute("user");
        session.removeAttribute("client_id");

        httpServletResponse.sendRedirect("/");
    }
}
