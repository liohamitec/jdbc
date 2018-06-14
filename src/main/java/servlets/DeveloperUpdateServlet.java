package servlets;

import model.Developer;
import model.Skill;
import service.DeveloperService;
import service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateDeveloper")
public class DeveloperUpdateServlet extends HttpServlet {
    DeveloperService devService;

    @Override
    public void init() throws ServletException {
        super.init();
        devService = (DeveloperService) getServletContext().getAttribute("devService");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Integer sal = Integer.parseInt(request.getParameter("salary"));

        devService.update(new Developer(id,name,age,sal));
        response.sendRedirect("developers");
        //request.getRequestDispatcher("skills").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
