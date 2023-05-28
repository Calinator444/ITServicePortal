package classes;

import com.example.itserviceportal.HelloServlet;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;
//import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
//import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.*;
import org.apache.struts2.ServletActionContext;

import javax.servlet.Servlet;
import java.sql.*;

public class ToLoginAction extends ActionSupport {
    public String execute() throws SQLException
    {
        return SUCCESS;
    }

}
