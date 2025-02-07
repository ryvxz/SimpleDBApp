package com.mycompany.simpledbapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        logoutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new LoginFrame();
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
