package com.mycompany.simpledbapp;

import javax.swing.*;

public class GuestFrame extends JFrame
{

    public GuestFrame()
    {
        setTitle("Guest View");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome, Guest!");
        add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        add(logoutButton);

        // Use lambda expression for logout action
        logoutButton.addActionListener(e ->
        {
            new LoginFrame();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
