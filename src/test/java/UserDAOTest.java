import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import com.facebookclone.dao.UserDAO;
import com.facebookclone.model.User;

class UserDAOTest {

    UserDAO userDAO = new UserDAO();

    @Test
    void getUser_testcase1() {
        User user = userDAO.getUser("example");
        assertNotNull(user); 
        assertEquals("example", user.getUsername()); 
        assertEquals("Example User", user.getFullname());
        assertEquals("example@example.com", user.getEmail()); 
    }
    
    @Test
    void getAllUsers_testcase2()
    {
    	List<User> users=userDAO.getAllUsers();
    	assertTrue(users.size()>0);
    }
    
    @Test
    void deleteUser_testcase3()
    {
    	Boolean status=userDAO.deleteUser("example");
    	assertTrue(status);
    }
    
    @Test
    void updateUser_testcase1() {
        
        User user = userDAO.getUser("example");
        assertNotNull(user);

        user.setFullname("Updated Example User");
        user.setEmail("updated@example.com");
        user.setPassword("newpassword123");

        User updatedUser = userDAO.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals("Updated Example User", updatedUser.getFullname());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals("newpassword123", updatedUser.getPassword());
    }
    
    @Test
    void loginUser_testcase1() {
        User user = userDAO.loginUser("example", "password");
        assertNotNull(user);
        
        assertEquals("example", user.getUsername());
        assertEquals("Example User", user.getFullname());
        assertEquals("example@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }
    
    @Test
    void registerUser_testcase1() {
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



