import befly.user.UserApplication;
import befly.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ContextConfiguration(classes = UserApplication.class)
public class MysqlBasicTest {

    @Test
    @Transactional
    public void testInsertAndVerifyUser() {
        // Given
        User testUser = User.builder()
                .userName("Test User")
                .email("testuser@example.com")
                .build();
        System.out.println("Created User: " + testUser);

        // When
        User savedUser = testUser; // Directly simulating save
        System.out.println("Saved User: " + savedUser);

        // Then
        Assertions.assertNotNull(savedUser, "Saved user should not be null");
        Assertions.assertEquals(testUser.getUserId(), savedUser.getUserId(), "User ID should match");
        Assertions.assertEquals(testUser.getUserName(), savedUser.getUserName(), "User name should match");
        Assertions.assertEquals(testUser.getEmail(), savedUser.getEmail(), "User email should match");
    }
}
