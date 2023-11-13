import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class BdConnector {
    static {
        try
        {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Failed to connect SQLite JDBC driver");
        }
    }
    public static Connection connect(String path)
    {
        try {
            return DriverManager.getConnection(path);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
