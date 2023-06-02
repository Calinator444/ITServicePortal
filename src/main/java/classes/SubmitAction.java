package classes;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import java.sql.SQLException;

public class SubmitAction extends ActionSupport implements ModelDriven<IssueBean> {

    private IssueBean model = new IssueBean();
    @Override
    public IssueBean getModel() {
        return this.model;
    }

    public void setModel(IssueBean model) {
        this.model = model;
    }

    @Override
    public String execute(){
        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            UserBean currentUser = (UserBean) session.get("currentUser");
            model.setCurrentUser(currentUser);

            System.out.println("method ran");
            String description = this.model.getIssueDescript();
            String title = this.model.getTitle();
            String category = this.model.getCategory();
            String user = currentUser.getUsername();
            System.out.println(user);


            DatabaseInterface.reportNewIssue(category, title, description);
        }
        catch (SQLException e)
        {

            //replace with error handling at some point
            System.out.println(e.getMessage());
        }
        return SUCCESS;
    }

    private void saveUserInSession(UserBean user) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put("currentUser", user);
    }
}
