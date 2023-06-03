package classes;

import com.opensymphony.xwork2.ActionSupport;
import jdk.jfr.Category;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.*;
public class StatisticsAction extends ActionSupport implements SessionAware {



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
            List<CategoryNumberBean> categoryNumber = DatabaseInterface.getCategoryNumbers();
            System.out.println(categoryNumber);
            sessionMap.put("Category", categoryNumber);

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return SUCCESS;
    }
}

