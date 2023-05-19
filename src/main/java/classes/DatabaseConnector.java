package classes;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import javax.sql.*;

public class DatabaseConnector {
    private static final DataSource ds = makeDataSource();
    private static DataSource makeDataSource() {
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:/comp/env/jdbc/MyConnection");
        }
        catch (NamingException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException
    {
        return ds.getConnection();
    }

}
