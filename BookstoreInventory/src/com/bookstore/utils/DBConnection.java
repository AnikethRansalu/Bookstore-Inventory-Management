package com.bookstore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USER = "root";  // Change if needed
    private static final String PASS = "pass123"; // Change to your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
