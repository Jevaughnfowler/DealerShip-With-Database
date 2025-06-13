package com.pluralsight.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection getConnection() throws SQLException {
        String url = System.getProperty("dbUrl");
        String user = System.getProperty("dbUser");
        String password = System.getProperty("dbPassword");

        return DriverManager.getConnection(url, user, password);
    }
}