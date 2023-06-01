package classes;

import com.example.itserviceportal.HelloServlet;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;
//import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
//import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.Servlet;
import java.sql.*;
import java.util.Map;
import java.util.List;
public class IssueAction extends ActionSupport {
    private List<IssueBean> issue;
    private List<String> issueTitles;

    public String execute() throws SQLException {
        issue = DatabaseInterface.getIssues();
        return SUCCESS;
    }

    public List<String> getIssueTitles() {
        for (IssueBean issuleList : issue) {
            String title = issuleList.getTitle();
            issueTitles.add(title);
        }
        return issueTitles;
    }
}
