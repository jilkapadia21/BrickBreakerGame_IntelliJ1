package testvsc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC connectivity details
public class DatabaseConnection {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/brickbreaker";
    private String username = "root";
    private String password = "sqllavina123";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}