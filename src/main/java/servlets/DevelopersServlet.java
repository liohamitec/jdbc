package servlets;

import model.Developer;
import service.DeveloperService;
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

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    DeveloperService devService;

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionUtil connUtil = new ConnectionUtil();
        Connection conn = connUtil.getConnection();

        devService = new DeveloperService(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Collection<Developer> devCollection = devService.getAll();

        request.setAttribute("devCollection", devCollection);
        request.getRequestDispatcher("developers.jsp").forward(request, response);

        /*
        PrintWriter out = response.getWriter();
        for (Developer developer : devCollection) {
            out.println(developer.toString());
        }
        */
    }
}
