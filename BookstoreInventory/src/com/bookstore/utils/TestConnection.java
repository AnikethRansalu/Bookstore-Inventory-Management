package com.bookstore.utils;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();
        
        if (conn != null) {
            System.out.println("✅ DB Connected!");
        } else {
            System.out.println("❌ DB Connection Failed!");
        }
    }
}
