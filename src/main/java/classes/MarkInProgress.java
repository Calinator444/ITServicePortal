package classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/MarkInProgress")
public class MarkInProgress extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();
        IssueBean issue = (IssueBean) session.getAttribute("Issue");
        UserBean user = (UserBean) session.getAttribute("User");
        String body = req.getParameter("body");
        try {
            DatabaseInterface.commentOnIssue(issue.getIssueId(), body, user.getUsername());
            DatabaseInterface.markIssueStatus(issue.getIssueId(), "waiting on reporter");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }



    }
}
