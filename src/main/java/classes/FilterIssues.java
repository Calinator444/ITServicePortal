package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
@WebServlet(urlPatterns = "/FilterIssues")
public class FilterIssues extends HttpServlet {

    @Override public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("end point reached");


        try {
            HttpSession session = req.getSession();
            //System.out.println(req.getParameter("tagId"));
            //System.out.println(req.getParameter("subTagId"));
            int tagId = Integer.parseInt(req.getParameter("tagId"));
            int subTagId = Integer.parseInt(req.getParameter("subTagId"));
            int userTagId = Integer.parseInt(req.getParameter("userTagid"));

            List<IssueBean> issues;
            System.out.println("created issue bean list");
            try {

                issues = DatabaseInterface.getFilteredIssues(tagId, subTagId);
                session.setAttribute("Issues", issues);
                System.out.println("attempting to send redirect");
                resp.sendRedirect("/ITServicesPortal/Home.action");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
