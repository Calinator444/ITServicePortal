package classes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {

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
                issue.setIssueDescript(rs.getString("issueDescript"));
                issue.setIssueID(rs.getInt("issueId"));
                issue.setReporter(rs.getString("reporter"));
                issue.setFixer(rs.getString("fixer"));
                issue.setIssueStatus(rs.getString("issueStatus"));
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

    public static void replyToComment(int commentId, String user, String body) throws SQLException
    {
        try(Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("INSERT INTO REPLIES VALUES(?, ?, ?)");
            ps.setInt(1, commentId);
            ps.setString(2, body);
            ps.setString(3, user);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw(e);
        }
    }

    public static void commentOnIssue(int issueId, String body, String user) throws SQLException
    {
        try(Connection con = DatabaseConnector.getConnection())
        {
            System.out.println("details: "+Integer.toString(issueId)+ body+user);
            PreparedStatement ps = con.prepareStatement("INSERT INTO COMMENTS VALUES(?,?,?,CURRENT_TIMESTAMP)");
            ps.setString(1, body);
            ps.setString(2, user);
            ps.setInt(3, issueId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            //System.out.println("sql error occurred");
            throw(e);
        }
    }

}
