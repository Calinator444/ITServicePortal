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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class LoginDetailsInterceptor extends AbstractInterceptor
{

    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        System.out.println("Login details interceptor fired");
        HttpSession session = (HttpSession) invocation.getInvocationContext().getSession();
        HttpServletResponse resp = (HttpServletResponse)invocation.getInvocationContext().getServletResponse();
        UserBean user = (UserBean) session.getAttribute("User");
        if(user == null)
            resp.sendRedirect("/ITServicesPortal/LoginAction.action");
        return invocation.invoke();
    }

}
