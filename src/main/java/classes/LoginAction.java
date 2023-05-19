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

public class LoginAction extends ActionSupport implements ConnectionAware, ModelDriven<UserBean> {

    private UserBean model = new UserBean();
    private String userUsername = null;
    private String userPassword = null;
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
        /*
        String username = this.model.getUsername();
        String password = this.model.getPassword();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM ENDUSER WHERE userUsername= ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("userUsername").equals(username))
            {
                System.out.println("user found!");
                this.userUsername = rs.getString("userUsername");
                this.userPassword = rs.getString("userPassword");
            }

        }*/
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
                if(!userEntry.getPassword().equals(password))
                    this.addFieldError("password", "Password does not match username");
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
