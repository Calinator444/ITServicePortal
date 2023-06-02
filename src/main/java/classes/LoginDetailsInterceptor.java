package classes;
import java.sql.*;

import classes.ConnectionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.RequestUtils;

import java.util.*;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



public class LoginDetailsInterceptor extends AbstractInterceptor
{

    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        System.out.println("Login details interceptor fired");
        //HttpServletRequest request = (HttpServletRequest)invocation.getInvocationContext().getServletRequest();
        //HttpServletResponse response = (HttpServletResponse)invocation.getInvocationContext().getServletResponse();
        //Map<String, Object>  session = invocation.getInvocationContext().getSession();
        //UserBean user = (UserBean) session.get("User");
        //if(user == null)
        //    response.sendRedirect("/ITServicesPortal/LoginAction.action");
        return invocation.invoke();
    }

}
