package com.mycompany.simpledbapp;

import java.sql.*;
import javax.swing.SwingUtilities;

public class SimpleDBApp
{

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
        String connStr = "jdbc:sqlite:src/main/resources/".concat(database); //jdbc:sqlite:src/main/resources/AccountsDB.db
        try
        {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e)
        {
            System.err.println("Failed to create connection");
            System.err.println(e.toString());
        }
    }

    public ResultSet getAll() throws SQLException
    {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM Users");
    }

    public User getUser(String username, String password) throws SQLException
    {
        String sqlStr = "SELECT * FROM Users WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return new User(rs.getString("username"), rs.getString("password"),
                    rs.getString("user_role"));
        }
        return null;
    }

//    public static void main(String[] args)
//    {
//        SimpleDBApp jdbc = new SimpleDBApp("root", "root", "AccountsDB.db");
//        try
//        {
//            ResultSet rs = jdbc.getAll();
//            while (rs.next())
//            {
//                System.out.println(rs.getString("username") + rs.getString("password"));
//            }
//        } catch (SQLException e)
//        {
//            System.err.println(e.toString());
//        }
//    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(LoginFrame::new);
    }

}
