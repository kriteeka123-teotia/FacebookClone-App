package com.facebookclone.dao;
import com.facebookclone.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/facebook_clone"; // Change if necessary
    private static final String JDBC_USERNAME = "root"; // Your MySQL username
    private static final String JDBC_PASSWORD = "password"; // Your MySQL password

    private Connection connection;

    // Constructor that establishes the database connection
    public UserDAO() {
        try {
            // Establishing the connection to MySQL
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users"; // SQL query to get all users

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Register a new user
    public void registerUser(User user) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)"; // SQL query to insert a user

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User login by verifying email and password
    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?"; // SQL query to check credentials

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
