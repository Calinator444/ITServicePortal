package classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet(urlPatterns = "/GetSubcategories")
public class GetSubcategories extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        int tagId = Integer.parseInt(req.getParameter("tagId"));
        HttpSession session = req.getSession();
        session.setAttribute("Subcategories", DatabaseInterface.getSubcategories(tagId));
        session.setAttribute("SelectedCategory",tagId);
    }
}
