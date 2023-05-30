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
                    String userRole = rs.getString("userRole");
                    ub.setUsername(userUsername);
                    ub.setPassword(userPassword);
                    ub.setRole(userRole);
                }
            }
            return null;
        }
        catch (SQLException e)
        {
            throw(e);
        }
    }

    public static List<IssueBean> getIssue() throws SQLException {
        List<IssueBean> issues = new ArrayList<>();
        //IssueBean[] issues = {};
        try (Connection con = DatabaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            //PreparedStatement ps = con.prepareStatement("SELECT * FROM ENDUSER WHERE userUsername= ?");
            //ps.setString(1, username);
            ResultSet rs = statement.executeQuery("SELECT * FROM ISSUES");
            while (rs.next()) {
                IssueBean issue = new IssueBean();
                issue.setIssueID(rs.getInt("issueId"));
                issue.setReporter(rs.getString("reporter"));
                issue.setFixer(rs.getString("fixer"));
                issue.setIssueStatus(rs.getString("issueStatus"));
                issue.setTitle(rs.getString("title"));
                issue.setIssueDescript(rs.getString("issueDescript"));
                issue.setResolution(rs.getString("resolution"));
                issue.setDateTimeReport(rs.getDate("dateTimeReport"));
                issue.setDateTimeResolved(rs.getDate("dateTimeSolved"));

                issues.add(issue);
            }
        }
        catch(SQLException e)
        {
            throw (e);
        }
        return issues;
        //return null;
    }
}
