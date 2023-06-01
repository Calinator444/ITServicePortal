package classes;
import javax.xml.transform.Result;
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
    public static IssueBean getIssue(String title)
    {
        IssueBean i = new IssueBean();
        List<CommentBean> comments = new ArrayList<CommentBean>();
        //CommentBean c;



        try(Connection con = DatabaseConnector.getConnection())
        {
            System.out.println("title provided to Database Interface: "+title);
            PreparedStatement ps = con.prepareStatement("SELECT ISSUES.issueId, reporter, fixer, issueStatus, title, issueDescript, dateTimeReport, dateTimeSolved, COMMENTS.commentId, body AS replyText, REPLIES.userUsername AS replyUser, commentText, COMMENTS.userUsername, dateSubmitted "+
                    "FROM ISSUES " +
                    "LEFT JOIN COMMENTS " +
                    "ON COMMENTS.issueId = ISSUES.issueId "+
                    "LEFT JOIN REPLIES ON REPLIES.commentId = COMMENTS.commentId " +
                    "WHERE title= ?");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int commentId = rs.getInt("commentId");
                //I really need to revise my SQL but this should work for this assignment, I feel like a different type of joint would've worked better though
                boolean commentAlreadyExists = false;
                for(CommentBean comment : comments)
                {
                    if(comment.getCommentId() == commentId)
                        commentAlreadyExists = true;
                }
                if(!commentAlreadyExists)
                {
                    CommentBean c = new CommentBean();
                    c.setCommentText(rs.getString("commentText"));
                    c.setUserUsername(rs.getString("userUsername"));
                    c.setCommentId(commentId);
                    System.out.println(rs.getString("commentText"));
                    comments.add(c);
                }
                CommentBean c = new CommentBean();
                System.out.println("matching article found");
                i.setTitle(rs.getString("title"));
                i.setIssueDescript(rs.getString("issueDescript"));//seriously, why tf was this column called issueDescript?
                i.setIssueId(rs.getInt("issueId"));


                String replyText = rs.getString("replyText");
                if(replyText != null) {
                    for (CommentBean comment : comments) {
                        if(comment.getCommentId() == commentId)
                        {
                            List<ReplyBean> replies = comment.getReplies();

                            if(replies == null)
                                replies = new ArrayList<ReplyBean>();
                            ReplyBean rb = new ReplyBean();
                            rb.setBody(rs.getString("replyUser"));
                            rb.setUser(rs.getString("replyText"));
                            replies.add(rb);
                            comment.setReplies(replies);
                        }

                    }
                }
            }
            i.setComments(comments);



        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(i.getIssueDescript());
        return i;
        //return null;
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
                issue.setIssueId(rs.getInt("issueId"));
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




    //I'll add an overload with the subcategory again soon
    {

    }
    public static List<IssueBean> getFilteredIssues(int tagId, int subTagId) throws SQLException
    {
        String query = "SELECT reporter, fixer, issueStatus, title, issueDescript, dateTimeReport, dateTimeSolved FROM ISSUES "+
        "INNER JOIN TAGS ON ISSUES.tagId = TAGS.tagId "+
        "INNER JOIN SUBTAGS ON SUBTAGS.subtagId = ISSUES.subTagId "+
        "WHERE ISSUES.subTagId = ? AND ISSUES.tagId = ?";
        List<IssueBean> issues = new ArrayList<IssueBean>();

        try(Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subTagId);
            ps.setInt(2, tagId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                IssueBean i = new IssueBean();
                i.setReporter(rs.getString("reporter"));
                i.setFixer(rs.getString("fixer"));
                i.setTitle(rs.getString("title"));
                i.setIssueDescript(rs.getString("issueDescript"));
                issues.add(i);
            }
        }
        catch (SQLException e) {
            throw (e);
        }
        return issues;
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
