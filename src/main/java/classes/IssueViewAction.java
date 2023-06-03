package classes;

import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;


import java.util.*;

public class IssueViewAction extends ActionSupport implements SessionAware {


    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map)
    {
        this.sessionMap = (SessionMap) map;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    @Override
    public String execute()
    {
        //title can be passed as a parameter\
        IssueBean i;
        i = DatabaseInterface.getIssue(title);


        //redirect them to no article page
        if(i == null || title == null)
        {
            if(title == null)
                System.out.println("title not found");
            if(i == null)
                System.out.println("article not found");
            System.out.println("parameter was missing or article not found");
            return ERROR;
        }
        else
            System.out.println("Item successfully mapped to session");
            System.out.println(i.getIssueStatus());
       sessionMap.put("Issue", i);
       return SUCCESS;

    }
}
