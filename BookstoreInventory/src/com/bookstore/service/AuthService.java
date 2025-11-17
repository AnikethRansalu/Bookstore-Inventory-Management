package com.bookstore.service;

import com.bookstore.model.User;

public class AuthService {

    public static User login(String username, String password) {
        // Temporary test user
        if(username.equals("admin") && password.equals("admin123")) {
            return new User(1, "admin", "admin123", 1); // roleId = 1
        }
        return null; // login failed
    }
}
