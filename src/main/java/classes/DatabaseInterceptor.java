package classes;
import java.sql.*;

import classes.ConnectionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import javax.naming.InitialContext;
import javax.sql.DataSource;





//This class has been
public class DatabaseInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception{
        Connection con = null;
        System.out.println("Intercept fired");
        Action action = (Action) invocation.getAction();
        if(action instanceof ConnectionAware) {
            System.out.println("action is connection aware");
            InitialContext ctx = new InitialContext();
            DataSource ds =  (DataSource) ctx.lookup("java:/comp/env/jdbc/MyConnection");
            con = ds.getConnection();
            ((ConnectionAware) action).setConnection(con);
        }
        String result = invocation.invoke();
        if(con != null)
            con.close();
        return result;// invocation.invoke();

    }

}
