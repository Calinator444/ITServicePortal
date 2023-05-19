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
}
