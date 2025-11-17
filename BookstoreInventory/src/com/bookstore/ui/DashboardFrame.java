package com.bookstore.ui;

import com.bookstore.model.User;
import javax.swing.*;

public class DashboardFrame extends JFrame {

    private User user;

    // Constructor accepts the logged-in user
    public DashboardFrame(User user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Welcome message with username
        JLabel label = new JLabel("Welcome, " + user.getUsername() + "!", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(18f));
        add(label);
    }
}
