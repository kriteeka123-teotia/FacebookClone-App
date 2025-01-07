import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.facebookclone.dao.UserDAO;
import com.facebookclone.model.User;

class UserDAOTest {

    UserDAO userDAO = new UserDAO();

    @Test
    void getUser_testcase1() throws ClassNotFoundException {
        User user = userDAO.getUser("example");
        assertNotNull(user); 
        assertEquals("example", user.getUsername()); 
        assertEquals("Example User", user.getFullname());
        assertEquals("example@example.com", user.getEmail()); 
    }
    
    @Test
    void getAllUsers_testcase2() throws ClassNotFoundException
    {
    	List<User> users=userDAO.getAllUsers();
    	assertTrue(users.size()>0);
    }
    
    @Test
    void deleteUser_testcase3() throws ClassNotFoundException
    {
    	Boolean status=userDAO.deleteUser("example");
    	assertTrue(status);
    }
    
    @Test
    void updateUser_testcase1() throws ClassNotFoundException {
        
        User user = userDAO.getUser("example");
        assertNotNull(user);

        user.setFullname("Updated Example User");
        user.setEmail("updated@example.com");
        user.setPassword("newpassword123");

        boolean updatedUser = userDAO.updateUser(user);

        assertNotNull(updatedUser);
    }
    
    @Test
    void loginUser_testcase1() throws SQLException, ClassNotFoundException {
        User user = userDAO.loginUser("example", "password");
        assertNotNull(user);
        
        assertEquals("example", user.getUsername());
        assertEquals("Example User", user.getFullname());
        assertEquals("example@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }
    
    @Test
    void registerUser_testcase1() throws SQLException, ClassNotFoundException {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setFullname("New User");
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password123");

        userDAO.registerUser(newUser);

        User retrievedUser = userDAO.getUser("newuser");
        assertNotNull(retrievedUser);
        assertEquals("newuser", retrievedUser.getUsername());
        assertEquals("New User", retrievedUser.getFullname());
        assertEquals("newuser@example.com", retrievedUser.getEmail());
        assertEquals("password123", retrievedUser.getPassword());
    }

}



