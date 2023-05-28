package classes;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {




    //maybe add comments to the bean later?
    public static IssueBean getIssue(String title)
    {
        IssueBean i = new IssueBean();

        try(Connection con = DatabaseConnector.getConnection())
        {
            System.out.println("title provided to Database Interface: "+title);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ISSUES WHERE title= ?");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println("matching article found");
                i.setTitle(rs.getString("title"));
                i.setIssueDescript(rs.getString("issueDescript"));//seriously, why tf was this column called issueDescript?
            }



        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(i.getIssueDescript());
        return i;
        //return null;
    }
    public static UserBean getUser(String username) throws SQLException
    {

        UserBean ub = new UserBean();
        try(Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ENDUSER WHERE userUsername= ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("userUsername").equals(username)) {
                    String userUsername = rs.getString("userUsername");
                    String userPassword = rs.getString("userPassword");
                    ub.setUsername(userUsername);
                    ub.setPassword(userPassword);
                    return ub;
                }
            }
            return null;
        }
        catch (SQLException e)
        {
            throw(e);
        }
    }


    //int cannot be l
    public static void reportNewIssue(String title, String description) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ISSUES VALUES(null, null, 'new', ?, ?, CURRENT_TIMESTAMP, null)");
            ps.setString(1, title);
            ps.setString(2, description);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw(e);
        }
    }
    public static List<IssueBean> getIssues() throws SQLException {
        List<IssueBean> issues = new ArrayList<IssueBean>();
        //IssueBean[] issues = {};
        try (Connection con = DatabaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            //PreparedStatement ps = con.prepareStatement("SELECT * FROM ENDUSER WHERE userUsername= ?");
            //ps.setString(1, username);
            ResultSet rs = statement.executeQuery("SELECT * FROM ISSUES");
            while (rs.next()) {
                IssueBean issue = new IssueBean();
                issue.setIssueDescript(rs.getString("body"));
                issue.setTitle(rs.getString("title"));
                issues.add(issue);
                //issues = ArrayFunctions.appendToArray(issues, issue);
            }
            return issues;
        }
        catch(SQLException e)
        {
            throw (e);
        }
        //return null;
    }

}
