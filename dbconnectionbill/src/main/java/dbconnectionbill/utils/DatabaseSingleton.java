package dbconnectionbill.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private static final String URL = "jdbc:mysql://localhost:3306/saasant_billing";
    private static final String USER = "root";
    private static final String PASSWORD = "asinstalin"; // Replace with your actual password

    private static DatabaseSingleton instance;
    private Connection connection;

    private DatabaseSingleton() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Database Connection Failed: " + e.getMessage());
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
