package com.mycompany.simpledbapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

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

        logoutButton.addActionListener(e ->
        {
            new LoginFrame();
            dispose();
        });

        loadUserList();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadUserList()
    {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/AccountsDB.db"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT username, user_role FROM Users"))
        {

            while (rs.next())
            {
                userList.append(rs.getString("username") + " - " + rs.getString("user_role") + "\n");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
