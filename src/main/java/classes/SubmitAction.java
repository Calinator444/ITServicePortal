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

            System.out.println("method ran");
            String description = this.model.getIssueDescript();
            String title = this.model.getTitle();
            String tag = this.model.getCategory();
            // Fetch the UserBean from the session
            UserBean user = (UserBean) session.get("User"); // Replace "username" with the key you used when you put the UserBean into the session.

            // Get the username from the UserBean
            String username = user.getUsername();

            System.out.println(username);
            System.out.println(tag);

            DatabaseInterface.reportNewIssue(title, description, username, tag);
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
