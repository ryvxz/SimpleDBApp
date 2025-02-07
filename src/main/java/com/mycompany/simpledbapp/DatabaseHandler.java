package com.mycompany.simpledbapp;

import java.sql.*;

public class DatabaseHandler
{

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/AccountsDB.db";
    private Connection conn;

    public DatabaseHandler()
    {
        try
        {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Database connected.");
        } catch (SQLException e)
        {
            System.err.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }

    public boolean isValidUser(String username, String password)
    {
        String sql = "SELECT user_role FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(String username)
    {
        String sql = "SELECT user_role FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                return rs.getString("user_role");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
