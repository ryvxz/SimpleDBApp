package com.mycompany.simpledbapp;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame
{

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private int failedAttempts = 0;
    private DatabaseHandler dbHandler;

    public LoginFrame()
    {
        dbHandler = new DatabaseHandler();

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // UI Components
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        // Use lambda expression for login button action
        loginButton.addActionListener(e -> handleLogin());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Handles the login process when the login button is clicked. Validates the
     * user's credentials and opens the appropriate frame based on the user
     * role.
     */
    private void handleLogin()
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (dbHandler.isValidUser(username, password))
        {
            String role = dbHandler.getUserRole(username);
            if ("admin".equals(role))
            {
                new AdminFrame();
            } else
            {
                new GuestFrame();
            }
            dispose();
        } else
        {
            failedAttempts++;
            JOptionPane.showMessageDialog(this,
                    "Invalid login attempt " + failedAttempts,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

            if (failedAttempts >= 3)
            {
                JOptionPane.showMessageDialog(this,
                        "Too many failed attempts! Exiting.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
}
