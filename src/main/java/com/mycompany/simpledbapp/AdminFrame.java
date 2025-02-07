package com.mycompany.simpledbapp;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame
{

    private DatabaseHandler dbHandler;
    private JTextArea userList;

    public AdminFrame()
    {
        dbHandler = new DatabaseHandler();

        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        userList = new JTextArea();
        userList.setEditable(false);
        add(new JScrollPane(userList), BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        add(logoutButton, BorderLayout.SOUTH);

        // Use lambda expression for logout action
        logoutButton.addActionListener(e ->
        {
            new LoginFrame();
            dispose();
        });

        loadUserList();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Loads the list of users from the database and displays them in the text
     * area.
     */
    private void loadUserList()
    {
        // Retrieve the formatted list of users from DatabaseHandler.
        String users = dbHandler.getAllUsersAsString();
        userList.setText(users);
    }
}
