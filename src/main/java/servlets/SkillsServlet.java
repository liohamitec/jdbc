package servlets;

import model.Skill;
import service.SkillService;
import util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {
    SkillService skillService;

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionUtil connUtil = new ConnectionUtil();
        Connection conn = connUtil.getConnection();

        skillService = new SkillService(conn);
        getServletContext().setAttribute("skillService", skillService);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Collection<Skill> skillCollection = skillService.getAll();

        request.setAttribute("skillCollection", skillCollection);
        request.getRequestDispatcher("skills.jsp").forward(request, response);

        /*
        PrintWriter out = response.getWriter();
        for (Skill skill : skillCollection) {
            out.println(skill.toString());
        }
        */
    }
}
