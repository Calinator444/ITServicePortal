package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/AddComment")
public class AddComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            UserBean user = (UserBean) session.getAttribute("User");
            IssueBean issue = (IssueBean) session.getAttribute("Issue");
            String title = issue.getTitle();
            String username = user.getUsername();
            //issue.getIssueId();

            int issueId = issue.getIssueId(); // req.getParameter("issueId");
            String body = req.getParameter("body");

            //placeholder username
            try {
                System.out.println("SQL was successful");
                DatabaseInterface.commentOnIssue(issueId, body, username);
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred");
                System.out.println(e.getMessage());
            }
            //only works for issue view page
            resp.sendRedirect("/ITServicesPortal/ViewIssue.action?title=" + title);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
