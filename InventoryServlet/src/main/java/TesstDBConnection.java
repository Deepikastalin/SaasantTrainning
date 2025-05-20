import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesstDBConnection {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/inventorydb";  // your DB URL
        String jdbcUsername = "root";                               // your DB username
        String jdbcPassword = "asinstalin";                         // your DB password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            if (conn != null) {
                System.out.println("✅ Connected to database successfully.");
                conn.close();
            } else {
                System.out.println("❌ Failed to connect.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed.");
            e.printStackTrace();
        }
    }
}
