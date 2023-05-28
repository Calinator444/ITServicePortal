package classes;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.sql.SQLException;

public class SubmitAction extends ActionSupport implements ModelDriven<IssueBean> {

    private IssueBean model = new IssueBean();
    @Override
    public IssueBean getModel() {
        return this.model;
    }

    @Override
    public String execute(){
        try {
            System.out.println("method ran");
            String description = this.model.getIssueDescript();
            String title = this.model.getTitle();
            DatabaseInterface.reportNewIssue(title, description);
        }
        catch (SQLException e)
        {

            //replace with error handling at some point
            System.out.println(e.getMessage());
        }
        return SUCCESS;
    }
}
