package classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/MarkCompleted")
public class MarkCompleted extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();
        IssueBean issue = (IssueBean) session.getAttribute("Issue");
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int issueId = issue.getIssueId();
        DatabaseInterface.markCompleted(commentId, issueId);
        //String username = req.getParameter("username");
    }
}
