package classes;

import com.example.itserviceportal.HelloServlet;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;
//import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
//import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.Servlet;
import java.sql.*;
import java.util.Map;

public class LoginAction extends ActionSupport implements ConnectionAware, ModelDriven<UserBean>, SessionAware {

    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map)
    {
        sessionMap = (SessionMap) map;
    }
    private UserBean model = new UserBean();
    @Override
    public UserBean getModel() {
        return model;
    }

    @Override
    public void setConnection(Connection con)
    {
        this.con = con;
    }
    private Connection con;
    @Override
    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(fieldName = "username", message = "Username must be filled in"),
                    @RequiredStringValidator(fieldName = "password", message = "Password must be filled in")
            }
    )
    public String execute() throws SQLException
    {
        //execute only runs on a successful login
        sessionMap.put("Issues", DatabaseInterface.getIssues());
        sessionMap.put("User", this.model);
        return SUCCESS;
    }
    //VALIDATIONS
    @Override
    public void validate(){
        String username = this.model.getUsername();
        String password = this.model.getPassword();
        try {
            UserBean userEntry = DatabaseInterface.getUser(username);
            if(userEntry != null)
            {
                if(!userEntry.getPassword().equals(password)) {
                    this.addFieldError("password", "Password does not match username");
                }
            }
            else
            {
                this.addFieldError("username", "Username was not found");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            addFieldError("username", "Server appears to be down");
        }

    }
}
