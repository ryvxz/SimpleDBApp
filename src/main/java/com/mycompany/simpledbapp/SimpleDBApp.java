package com.mycompany.simpledbapp;

import javax.swing.SwingUtilities;
import java.sql.*;

public class SimpleDBApp
{

    /**
     * Represents a user with a username, password, and access role.
     */
    private class User
    {

        String username;
        String password;
        String accessRole;

        public User(String username, String password, String accessRole)
        {
            this.username = username;
            this.password = password;
            this.accessRole = accessRole;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getAccessRole()
        {
            return accessRole;
        }

        public void setAccessRole(String accessRole)
        {
            this.accessRole = accessRole;
        }
    }

    private Connection conn;

    public SimpleDBApp(String username, String password, String database)
    {
        String connStr = "jdbc:sqlite:src/main/resources/".concat(database); // Example: jdbc:sqlite:src/main/resources/AccountsDB.db
        try
        {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e)
        {
            System.err.println("Failed to create connection");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a ResultSet containing all users
     * @throws SQLException if a database access error occurs
     */
    public ResultSet getAll() throws SQLException
    {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM Users");
    }

    /**
     * Retrieves a user matching the given username and password.
     *
     * @param username the username to search for
     * @param password the password to match
     * @return a User object if found, or null otherwise
     * @throws SQLException if a database access error occurs
     */
    public User getUser(String username, String password) throws SQLException
    {
        String sqlStr = "SELECT * FROM Users WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return new User(rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("user_role"));
        }
        return null;
    }

    /*
    SwingUtilities.invokeLater() places your code in the FIFO Queue of the event-dispatch thread (EDT)
    , so it will be executed from the EDT whenever it has finished the other tasks it was doing.
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
