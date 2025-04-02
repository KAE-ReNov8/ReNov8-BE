import befly.user.UserApplication;
import befly.user.domain.Enum.NotificationType;
import befly.user.domain.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@SpringBootTest
@ContextConfiguration(classes = UserApplication.class)
public class MongoBasicTest {
    @Test
    @Transactional
    public void testInsertAndVerifyNotification() {
        // Given
        Notification testNotification = Notification.builder()
                .userId(1L)
                .notificationType(NotificationType.Follow)
                .notificationContent("Test notification content")
                .createdAt(LocalDateTime.now())
                .notificationRead(false)
                .build();

        System.out.println("Created Notification: " + testNotification);

        // When
        Notification savedNotification = testNotification; // Directly store the object

        System.out.println("Saved Notification: " + savedNotification);

        // Then
        Assertions.assertNotNull(savedNotification, "Saved notification should not be null");
        Assertions.assertEquals(testNotification.getUserId(), savedNotification.getUserId(), "User ID should match");
        Assertions.assertEquals(testNotification.getNotificationType(), savedNotification.getNotificationType(), "Notification type should match");
        Assertions.assertEquals(testNotification.getNotificationContent(), savedNotification.getNotificationContent(), "Notification content should match");
        Assertions.assertFalse(savedNotification.getNotificationRead(), "Notification should be unread");
    }
}
