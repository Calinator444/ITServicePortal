package classes;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.util.*;

public class StaffPageAction extends ActionSupport implements SessionAware {


    private SessionMap sessionMap;
    @Override
    public void setSession(Map<String, Object> map)
    {
        sessionMap = (SessionMap) map;
    }
    @Override
    public String execute()
    {
        UserBean ub = (UserBean) sessionMap.get("User");
        String username = ub.getUsername();
        List<IssueBean> issuesForStaff = DatabaseInterface.getIssuesAssignedToStaff(username);
        System.out.println(Integer.toString(issuesForStaff.size()));
        sessionMap.put("IssuesForStaff", issuesForStaff);
        return SUCCESS;
    }
}
