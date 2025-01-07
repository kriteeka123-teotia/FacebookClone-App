package com.facebookclone.dao;

import com.facebookclone.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	 
	        final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/facebook_clone";
	        final String JDBC_USERNAME = "root";
	        final String JDBC_PASSWORD = "Teotia@123";
	        
	        conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	        System.out.println("Database connection established successfully!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error establishing database connection.", e);
	    }
	    return conn;
	}

    public List<User> getAllUsers() throws ClassNotFoundException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users"; 

        try (Connection conn = getConnection();
        		Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(String username) throws ClassNotFoundException {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public int getGeneratedUserId(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("User ID not found for username: " + username);
    }


    public boolean updateUser(User user) throws ClassNotFoundException {
    	boolean isUpdated = false;
    	Connection conn = null;
        PreparedStatement stmt = null;
    	String query = "UPDATE users SET fullname = ?, email = ?, bio = ? WHERE id = ?";

        try {
        	conn = getConnection();
    		stmt = conn.prepareStatement(query);
            
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getBio());
            stmt.setInt(4, user.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0)  {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }
    
    public boolean deleteUser(String username) throws ClassNotFoundException {
        String query = "DELETE FROM users WHERE username = ?";
        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void registerUser(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the auto-generated ID
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1)); // Set the auto-generated user ID
                }
            }
        }
    }

    public User loginUser(String username, String password) throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM users WHERE username = ? AND password = ?"; 

        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
        }

        return null;
    }
    
}
