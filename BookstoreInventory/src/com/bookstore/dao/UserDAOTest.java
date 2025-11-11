package com.bookstore.dao;

import com.bookstore.model.User;

public class UserDAOTest {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.login("admin", "admin123");

        if (user != null) {
            System.out.println("✅ Login success → " + user.getUsername());
        } else {
            System.out.println("❌ Login failed");
        }
    }
}
