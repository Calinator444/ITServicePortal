package classes;
import java.sql.*;

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
    public static IssueBean[] getIssues() throws SQLException {

        IssueBean[] issues = {};
        try (Connection con = DatabaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            //PreparedStatement ps = con.prepareStatement("SELECT * FROM ENDUSER WHERE userUsername= ?");
            //ps.setString(1, username);
            ResultSet rs = statement.executeQuery("SELECT * FROM ISSUES");
            while (rs.next()) {
                IssueBean issue = new IssueBean();
                issue.setIssueDescript(rs.getString("body"));
                issue.setTitle(rs.getString("title"));
                issues = ArrayFunctions.appendToArray(issues, issue);
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
