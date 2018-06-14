package servlets;

import model.Developer;
import service.DeveloperService;
import service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteDeveloper")
public class DeveloperDeleteServlet extends HttpServlet {
    DeveloperService devService;

    @Override
    public void init() throws ServletException {
        super.init();
        devService = (DeveloperService) getServletContext().getAttribute("devService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        devService.delete(id);
        response.sendRedirect("developers");
        //request.getRequestDispatcher("skills").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}