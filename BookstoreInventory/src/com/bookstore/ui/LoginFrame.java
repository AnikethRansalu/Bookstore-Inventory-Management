package com.bookstore.ui;

import com.bookstore.model.User;
import com.bookstore.service.AuthService;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JButton btnLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblUsername;
    private JLabel lblPassword;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {

        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Login button listener
        btnLogin.addActionListener(evt -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            User user = AuthService.login(username, password);
            if (user != null) {
                // Open Dashboard with user info
                new DashboardFrame(user).setVisible(true);
                this.dispose(); // close login window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword)
                    .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50)
                .addComponent(lblUsername)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(lblPassword)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30)
                .addComponent(btnLogin)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
