package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/AddReply")

public class AddReply extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request received!");
        HttpSession session = req.getSession();
        IssueBean issue = (IssueBean) session.getAttribute("Issue");
        String title = issue.getTitle();

        String body = req.getParameter("body");
        UserBean user = (UserBean) session.getAttribute("User");

        int commentId = Integer.parseInt(req.getParameter("commentId"));
        String username = user.getUsername();

        try {
            DatabaseInterface.replyToComment(commentId, username, body);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        resp.sendRedirect("/ITServicesPortal/ViewIssue.action?title="+title);
    }

}
