import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.facebookclone.dao.UserDAO;
import com.facebookclone.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class UserDAOTest {

    private UserDAO userDAO = new UserDAO();

    // Initialize database connection directly in the test
    private Connection getTestConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/facebook_clone", // Update the database name if required
            "root",
            "Teotia@123"
        );
    }

    @Test
    void registerUser_test() {
        try (Connection conn = getTestConnection()) {
            // Initialize the UserDAO with the test connection if necessary
            User user = new User();
            user.setUsername("testRegister");
            user.setEmail("testRegister@example.com");
            user.setPassword("password123");

            // Check if user already exists
            if (userDAO.getUser("testRegister") == null) {
                userDAO.registerUser(user);
            }

            User registeredUser = userDAO.getUser("testRegister");
            assertNotNull(registeredUser, "Registered user should not be null");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }


    @Test
    void loginUser_test() throws ClassNotFoundException, SQLException {
        try (Connection conn = getTestConnection()) {
            // Check if the user already exists
            String checkUserQuery = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkUserQuery)) {
                stmt.setString(1, "John Doe");
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    // User doesn't exist, so insert it
                    String insertUserQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertUserQuery)) {
                        insertStmt.setString(1, "John Doe");
                        insertStmt.setString(2, "johndoe@example.com");
                        insertStmt.setString(3, "password123");
                        insertStmt.executeUpdate();
                    }
                }
            }

            // Now attempt to login
            User user = userDAO.loginUser("John Doe", "password123");
            assertNotNull(user, "Login user should not be null");
        }
    }




    @Test
    void selectUser_test() {
        try (Connection conn = getTestConnection()) {
            User user = userDAO.getUser("John Doe"); // Ensure this username exists in your DB
            assertNotNull(user, "Selected user should not be null");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void selectAllUsers_test() {
        try (Connection conn = getTestConnection()) {
            List<User> users = userDAO.getAllUsers();
            assertNotNull(users, "Users list should not be null");
            assertTrue(users.size() > 0, "Users list should not be empty");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void updateUser_test() {
        try (Connection conn = getTestConnection()) {
            User user = userDAO.getUser("testUser");

            // If the user doesn't exist, create and add the user for testing
            if (user == null) {
                user = new User();
                user.setUsername("testUser");
                user.setEmail("testUser@example.com");
                user.setPassword("password123");
                userDAO.registerUser(user);  // Ensure you have a registerUser method to add a new user
            }

            assertNotNull(user, "User should exist before update");

            // Update the user
            user.setEmail("updatedEmail@example.com");
            boolean isUpdated = userDAO.updateUser(user);

            assertTrue(isUpdated, "User should be updated successfully");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }


    @Test
    void deleteUser_test() {
        try (Connection conn = getTestConnection()) {
            User user = userDAO.getUser("John Doe"); // Ensure this username exists in your DB
            assertNotNull(user, "User should exist before deletion");

            boolean isDeleted = userDAO.deleteUser(user.getUsername());
            assertTrue(isDeleted, "User should be deleted successfully");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
