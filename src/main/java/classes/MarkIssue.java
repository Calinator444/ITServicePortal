package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/MarkIssue")
public class MarkIssue extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {


            HttpSession session = req.getSession();
            IssueBean issue = (IssueBean) session.getAttribute("Issue");
            //IssueBean issueBean = (IssueBean) req.getAttribute("Issue");
            int issueId = issue.getIssueId();
            System.out.println("try statement successful");
            String status = req.getParameter("status");
            try {
                DatabaseInterface.markIssueStatus(issueId, status);

            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }


}
