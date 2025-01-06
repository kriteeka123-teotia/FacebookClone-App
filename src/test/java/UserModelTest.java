import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.facebookclone.model.User;

class UserModelTest {

    @Test
    void testUserSettersAndGetters() {
        User user = new User();
        user.setId(1);
        user.setUsername("example");
        user.setFullname("Example User");
        user.setEmail("example@example.com");
        user.setPassword("password");

        assertEquals(1, user.getId());
        assertEquals("example", user.getUsername());
        assertEquals("Example User", user.getFullname());
        assertEquals("example@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    void testUserObjectEquality() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("example");
        user1.setFullname("Example User");
        user1.setEmail("example@example.com");
        user1.setPassword("password");

        User user2 = new User();
        user2.setId(1);
        user2.setUsername("example");
        user2.setFullname("Example User");
        user2.setEmail("example@example.com");
        user2.setPassword("password");

        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getUsername(), user2.getUsername());
        assertEquals(user1.getFullname(), user2.getFullname());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }

    @Test
    void testUserDefaultValues() {
        User user = new User();

        assertEquals(0, user.getId());
        assertNull(user.getUsername());
        assertNull(user.getFullname());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }
}
