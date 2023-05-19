package classes;

import java.sql.Connection;




//classes that implement this interface will automatically have a database connection assigned through "Database Interceptor"
public interface ConnectionAware {
    public void setConnection(Connection con);

}
