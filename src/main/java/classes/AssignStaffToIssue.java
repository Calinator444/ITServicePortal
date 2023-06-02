package classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/AssignStaffToIssue")
public class AssignStaffToIssue extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        int issueId = Integer.parseInt(req.getParameter("issueId"));
        String staffMember = req.getParameter("username");
        try {
            DatabaseInterface.assignStaffToIssue(staffMember, issueId);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
