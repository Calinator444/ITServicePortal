package classes;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.util.*;

public class IssueReporting extends ActionSupport implements SessionAware {

    private SessionMap sessionMap;
    @Override
    public void setSession(Map<String,Object> map)
    {
        this.sessionMap = (SessionMap) map;
    }
    @Override public String execute()
    {
        if(sessionMap.get("Subcategories") == null)
            sessionMap.put("Subcategories", DatabaseInterface.getSubcategories(1));
        return SUCCESS;
    }
}
