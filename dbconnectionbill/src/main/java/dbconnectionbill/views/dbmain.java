package dbconnectionbill.views;
import dbconnectionbill.utils.DatabaseSingleton;

import java.sql.*;
import java.util.*;
public class dbmain {
	public static void main(String[] args) {
        Connection conn = DatabaseSingleton.getInstance().getConnection();
        if (conn != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect!");
        }
    }
}
