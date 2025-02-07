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

    /**
     * Checks if the given username and password match a user in the database.
     *
     * @param username the username to validate
     * @param password the password to validate
     * @return true if a matching user exists, false otherwise
     */
    public boolean isValidUser(String username, String password)
    {
        String sql = "SELECT user_role FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // Use nested try-with-resources to auto-close the ResultSet.
            try (ResultSet rs = pstmt.executeQuery())
            {
                return rs.next();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the user role for a given username.
     *
     * @param username the username whose role is to be retrieved
     * @return the user role as a String, or null if not found
     */
    public String getUserRole(String username)
    {
        String sql = "SELECT user_role FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery())
            {
                if (rs.next())
                {
                    return rs.getString("user_role");
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database and returns them as a formatted
     * string.
     *
     * @return a string containing the username and role of each user
     */
    public String getAllUsersAsString()
    {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT username, user_role FROM Users";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                sb.append(rs.getString("username"))
                        .append(" - ")
                        .append(rs.getString("user_role"))
                        .append("\n");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
