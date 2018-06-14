package servlets;

import model.Skill;
import service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSkill")
public class SkillDeleteServlet extends HttpServlet {
    SkillService skillService;


    @Override
    public void init() throws ServletException {
        super.init();
        skillService = (SkillService) getServletContext().getAttribute("skillService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        skillService.delete(id);
        response.sendRedirect("skills");
        //request.getRequestDispatcher("skills").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
