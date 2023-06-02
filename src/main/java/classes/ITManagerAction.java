package classes;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.*;
public class ITManagerAction extends ActionSupport implements SessionAware {



    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map)
    {
        sessionMap = (SessionMap) map;
    }
    @Override
    public String execute()
    {
        try {
            List<IssueBean> issues = DatabaseInterface.getissuesWithStatus("new");
            List<UserBean> staff = DatabaseInterface.getUsersWithRole("ITStaff");
            for(IssueBean issue : issues)
            {
                System.out.println(issue.getTitle());
            }
            sessionMap.put("NewIssues", issues);
            sessionMap.put("ITStaff", staff);

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return SUCCESS;
    }
}
