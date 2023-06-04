package classes;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {


    public static void assignStaffToIssue(String username, int issueId) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ISSUES SET fixer= ?,issueStatus = 'in progress' WHERE issueId = ?");
            ps.setString(1, username);
            ps.setInt(2, issueId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw (e);
        }
    }

    public static UserBean getUser(String username) throws SQLException {

        UserBean ub = new UserBean();
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT firstName, lastName, email, contactNo, ENDUSER.userUsername, userPassword, userRole  FROM ENDUSER " +
                    "INNER JOIN ROLE ON ROLE.userUsername = ENDUSER.userUsername " +
                    "WHERE ENDUSER.userUsername = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //UserBean userBean = new UserBean();
                //if (rs.getString("userUsername").equals(username)) {
                //    String userUsername = rs.getString("userUsername");
                //    String userPassword = rs.getString("userPassword");
                //    ub.setUsername(userUsername);
                //    ub.setPassword(userPassword);
                //return ub;
                UserBean userBean = new UserBean();
                userBean.setFirstName(
                        rs.getString("firstName"));
                userBean.setLastName(
                        rs.getString("lastName"));
                userBean.setContactNo(
                        rs.getInt("contactNo"));
                userBean.setUsername(
                        rs.getString("userUsername"));
                userBean.setPassword(
                        rs.getString("userPassword"));
                userBean.setRole(
                        rs.getString("userRole"));
                return userBean;
            }
            return null;
        } catch (SQLException e) {
            throw (e);
        }
    }

    public static String getUserRole(String username) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT userRole FROM ENDUSER WHERE userUsername = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("userRole");
            } else {
                return null; // or throw an exception, depending on your requirements
            }
        } catch (SQLException e) {
            throw e;
        }
    }


    public static IssueBean getIssue(String title) {
        IssueBean i = new IssueBean();
        List<CommentBean> comments = new ArrayList<CommentBean>();
        //CommentBean c;


        try (Connection con = DatabaseConnector.getConnection()) {
            System.out.println("title provided to Database Interface: " + title);
            PreparedStatement ps = con.prepareStatement("SELECT ISSUES.issueId, reporter, fixer, issueStatus, title, issueDescript, dateTimeReport, dateTimeSolved, COMMENTS.commentId, body AS replyText, REPLIES.userUsername AS replyUser, commentText, COMMENTS.userUsername, dateSubmitted " +
                    "FROM ISSUES " +
                    "LEFT JOIN COMMENTS " +
                    "ON COMMENTS.issueId = ISSUES.issueId " +
                    "LEFT JOIN REPLIES ON REPLIES.commentId = COMMENTS.commentId " +
                    "WHERE title= ?");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int commentId = rs.getInt("commentId");
                //I really need to revise my SQL but this should work for this assignment, I feel like a different type of joint would've worked better though
                boolean commentAlreadyExists = false;
                for (CommentBean comment : comments) {
                    if (comment.getCommentId() == commentId)
                        commentAlreadyExists = true;
                }
                if (!commentAlreadyExists) {
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
                if (replyText != null) {
                    for (CommentBean comment : comments) {
                        if (comment.getCommentId() == commentId) {
                            List<ReplyBean> replies = comment.getReplies();

                            if (replies == null)
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


        } catch (SQLException e) {
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
                issue.setDateTimeResolved(rs.getDate("dateTimeSolved"));
                issues.add(issue);
                //issues = ArrayFunctions.appendToArray(issues, issue);
            }
            return issues;
        } catch (SQLException e) {
            throw (e);
        }
        //return null;
    }

    public static void reportNewIssue(String title, String description, String user, String tag) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ISSUES VALUES(?, null, 'new', ?, ?, CURRENT_TIMESTAMP, null, ?, null)");
            ps.setString(1, user);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setInt(4, Integer.parseInt(tag));
            int affectedRows = ps.executeUpdate();
            System.out.println("Inserted new issue with title: " + title + ", and description: " + description + ". Affected rows: " + affectedRows);

        } catch (SQLException e) {
            throw (e);
        }
    }


    //I'll add an overload with the subcategory again soon
    {

    }

    public static List<IssueBean> getFilteredIssues(int tagId, int subTagId) throws SQLException {
        String query = "SELECT reporter, fixer, issueStatus, title, issueDescript, dateTimeReport, dateTimeSolved FROM ISSUES " +
                "INNER JOIN TAGS ON ISSUES.tagId = TAGS.tagId " +
                "INNER JOIN SUBTAGS ON SUBTAGS.subtagId = ISSUES.subTagId " +
                "WHERE ISSUES.subTagId = ? AND ISSUES.tagId = ?";
        List<IssueBean> issues = new ArrayList<IssueBean>();

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subTagId);
            ps.setInt(2, tagId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IssueBean i = new IssueBean();
                i.setReporter(rs.getString("reporter"));
                i.setFixer(rs.getString("fixer"));
                i.setTitle(rs.getString("title"));
                i.setIssueDescript(rs.getString("issueDescript"));
                issues.add(i);
            }
        } catch (SQLException e) {
            throw (e);
        }
        return issues;
    }

    public static void replyToComment(int commentId, String user, String body) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO REPLIES VALUES(?, ?, ?)");
            ps.setInt(1, commentId);
            ps.setString(2, body);
            ps.setString(3, user);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw (e);
        }
    }

    public static void commentOnIssue(int issueId, String body, String user) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            System.out.println("details: " + Integer.toString(issueId) + body + user);
            PreparedStatement ps = con.prepareStatement("INSERT INTO COMMENTS VALUES(?,?,?,CURRENT_TIMESTAMP)");
            ps.setString(1, body);
            ps.setString(2, user);
            ps.setInt(3, issueId);
            ps.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("sql error occurred");
            throw (e);
        }
    }

    public static void markIssueStatus(int issueId, String status) throws SQLException {
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ISSUES SET issueStatus = ? WHERE issueId = ?");
            ps.setString(1, status);
            ps.setInt(2, issueId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw (e);
        }
    }
    public static void markCompleted(int commentId, int issueId)
    {
        try(Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE TABLE ISSUES set resolution = ?, dateTimeSolved = CURRENT_TIMESTAMP issueStatus  = 'resolved' WHERE issueId = ?");
            ps.setInt(1, commentId);
            ps.setInt(2,issueId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static List<Subcategory> getSubcategories(int tagId)
    {

        List<Subcategory> subcategories = new ArrayList<Subcategory>();
        try(Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT subtagType, subtagId FROM SUBTAGS INNER JOIN TAGS ON TAGS.tagId = SUBTAGS.tagId WHERE TAGS.tagId = ?");
            ps.setInt(1, tagId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String subtagType = rs.getString("subtagType");
                String subtagId = rs.getString("subtagId");
                subcategories.add(new Subcategory(subtagId, subtagType));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return subcategories;
    }

    public static List<IssueBean> getIssuesAssignedToStaff(String username)
    {
        List<IssueBean> issues = new ArrayList<IssueBean>();
        String statement = "SELECT reporter, fixer, issueStatus, title, issueDescript, dateTimeReport, dateTimeSolved FROM ISSUES "+
                "INNER JOIN TAGS ON ISSUES.tagId = TAGS.tagId "+
                "INNER JOIN SUBTAGS ON SUBTAGS.subtagId = ISSUES.subTagId "+
                "WHERE fixer = ?";
        try(Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {


                System.out.println("found issue");
                IssueBean i = new IssueBean();
                i.setReporter(rs.getString("reporter"));
                i.setFixer(rs.getString("fixer"));
                i.setIssueStatus(rs.getString("issueStatus"));
                i.setTitle(rs.getString("title"));
                i.setIssueDescript(rs.getString("issueDescript"));
                i.setDateTimeReport(rs.getDate("dateTimeReport"));
                //well get the date/time it was reported later maybe?
                //i.setDateTimeResolved();
                issues.add(i);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return issues;
    }
    public static List<Tag> getTags()
    {
        List<Tag> tags = new ArrayList<Tag>();
        try(Connection con = DatabaseConnector.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USERTAGS");
            while (rs.next())
            {
                tags.add(new Tag(rs.getString("tagId"), rs.getString("tag")));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return tags;
    }
    public static void reportNewIssue(String reporter, String title, String description, int tagId, int subTagId, List<String> tags) throws SQLException {
        List<Integer> tagKeys = new ArrayList<Integer>();
        int insertedId = -1;
        try (Connection con = DatabaseConnector.getConnection())
        {
            PreparedStatement ps;
            for(String tag : tags)
            {
                ps = con.prepareStatement("IF NOT EXISTS(SELECT * FROM USERTAGS WHERE tag = ?) "+
                        "BEGIN "+
                        "INSERT INTO USERTAGS VALUES (?) "+
                        "END "+
                        "ELSE "+
                        "BEGIN "+
                        "SELECT tagId FROM USERTAGS WHERE tag = ? "+
                        "END", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,tag);
                ps.setString(2,tag);
                ps.setString(3, tag);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    System.out.println("Select operation was ran");
                    System.out.println(rs.getString(1));
                    tagKeys.add(rs.getInt(1));
                }
                else {
                    System.out.println("Insert operation was ran");
                    rs = ps.getGeneratedKeys();
                    if (rs.next())
                        tagKeys.add(rs.getInt(1));
                }
            }
            for(int key : tagKeys)
            {
                System.out.println(key);
            }
            ps = con.prepareStatement("INSERT INTO ISSUES VALUES(?, null, 'new', ?, ?, CURRENT_TIMESTAMP, null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, reporter);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setInt(4,tagId);
            ps.setInt(5,subTagId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next())
                insertedId = rs.getInt(1);
            for(int key : tagKeys)
            {
                System.out.println("adding tuple to user tags");
                ps = con.prepareStatement("INSERT INTO ISSUEUSERTAG VALUES(?,?)");
                ps.setInt(1, insertedId);
                ps.setInt(2, key);
                ps.executeUpdate();
            }
        }
        catch(SQLException e)
        {
            throw(e);
        }
    }

    public static List<IssueBean> getissuesWithStatus(String status) throws SQLException {
        List<IssueBean> issues = new ArrayList<IssueBean>();
        String query = "SELECT * FROM ISSUES WHERE issueStatus = ?";
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IssueBean issue = new IssueBean();
                issue.setIssueDescript(rs.getString("issueDescript"));
                issue.setIssueId(rs.getInt("issueId"));
                issue.setReporter(rs.getString("reporter"));
                issue.setFixer(rs.getString("fixer"));
                issue.setIssueStatus(rs.getString("issueStatus"));
                issue.setTitle(rs.getString("title"));
                issues.add(issue);
            }
        } catch (SQLException e) {
            throw (e);
        }
        return issues;
    }

    public static List<UserBean> getUsersWithRole(String role) throws SQLException {

        List<UserBean> users = new ArrayList<UserBean>();
        String query = "SELECT firstName, lastName, email, contactNo, ENDUSER.userUsername, userPassword, userRole  FROM ENDUSER " +
                "INNER JOIN ROLE ON ROLE.userUsername = ENDUSER.userUsername " +
                "WHERE userRole = ?";
        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setFirstName(
                        rs.getString("firstName"));
                userBean.setLastName(
                        rs.getString("lastName"));
                userBean.setContactNo(
                        rs.getInt("contactNo"));
                userBean.setUsername(
                        rs.getString("userUsername"));
                userBean.setPassword(
                        rs.getString("userPassword"));
                userBean.setRole(
                        rs.getString("userRole"));
                users.add(userBean);
            }

        } catch (SQLException e) {
            throw (e);
        }
        return users;
    }

    public static List<CategoryNumberBean> getCategoryNumbers() throws SQLException {
        List<CategoryNumberBean> categoryCount = new ArrayList<CategoryNumberBean>();

        String query = "SELECT tagId, COUNT(*) as count FROM ISSUES GROUP BY tagId";

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                CategoryNumberBean issueCategory = new CategoryNumberBean();
                issueCategory.setCategory(rs.getInt("tagId"));
                issueCategory.setCount(rs.getInt("count"));
                categoryCount.add(issueCategory);
            }


        } catch (SQLException e) {
            // Handle exception
        }
        return categoryCount;
    }

    public static List<StatusNumberBean> getNumberOfIssuesInEachStatus() throws SQLException {
        List<StatusNumberBean> eachStatusIssueCount = new ArrayList<StatusNumberBean>();

        String query = "SELECT issueStatus, COUNT(*) as count FROM ISSUES GROUP BY issueStatus";

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                StatusNumberBean issueStatusCount = new StatusNumberBean();
                issueStatusCount.setStatus(rs.getString("issueStatus"));
                issueStatusCount.setStatusCount(rs.getInt("count"));
                eachStatusIssueCount.add(issueStatusCount);
            }

        } catch (SQLException e) {
            // Handle exception
        }
        return eachStatusIssueCount;
    }


    public static List<fixerBean> getIssuesItStaff() throws SQLException {
        List<fixerBean> issuesStaff = new ArrayList<fixerBean>();

        String query = "SELECT fixer, COUNT(*) as count FROM ISSUES GROUP BY fixer";

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                fixerBean fixer = new fixerBean();
                fixer.setFixer(rs.getString("fixer"));
                fixer.setFixerCount(rs.getInt("count"));
                issuesStaff.add(fixer);
            }

        } catch (SQLException e) {
            // Handle exception
        }
        return issuesStaff;
    }


    public static List<IssueLast30Days> avgResolvedTime() throws SQLException {
        List<IssueLast30Days> last30Days = new ArrayList<IssueLast30Days>();

        String query = "SELECT * WHERE dateTimeSolved IS NOT NULL";

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                IssueLast30Days issueLast = new IssueLast30Days();
                issueLast.setBeginDate(rs.getDate("dateTimeReport"));
                issueLast.setEndDate(rs.getDate("dateTimeSolved"));
                last30Days.add(issueLast);
            }

        } catch (SQLException e) {
            // Handle exception
        }
        System.out.print(last30Days);
        return last30Days;
    }

    public static List<topFiveUnresolved> topFiveUnresolved() throws SQLException {
        List<topFiveUnresolved> topFive = new ArrayList<topFiveUnresolved>();

        String query = "SELECT TOP 5 * FROM ISSUES WHERE dateTimeSolved IS NULL ORDER BY dateTimeSolved ASC";

        try (Connection con = DatabaseConnector.getConnection()) {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                topFiveUnresolved topFiveUnRe = new topFiveUnresolved();
                topFiveUnRe.setName(rs.getString("title"));
                topFiveUnRe.setBeginDate(rs.getDate("dateTimeReport"));
                topFive.add(topFiveUnRe);
            }

        } catch (SQLException e) {
            // Handle exception
        }
        System.out.println(topFive);
        return topFive;
    }
}



