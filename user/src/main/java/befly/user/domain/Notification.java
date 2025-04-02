package befly.user.domain;

import befly.user.domain.Enum.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notification") // MongoDB 컬렉션 이름
public class Notification {
    /** 알림 받을 사용자 ID */
    @Field(name = "user_id") // 실제 MongoDB 필드 이름
    private Long userId;

    /** 알림 유형 (ENUM 타입으로 정의) */
    @Field(name = "notification_type")
    private NotificationType notificationType;

    /** 알림 내용 */
    @Field(name = "notification_content")
    private String notificationContent;

    /** 알림 생성 날짜 */
    @Field(name = "created_at")
    private LocalDateTime createdAt;

    /** 읽음 여부 (true: 읽음, false: 안 읽음) */
    @Field(name = "notification_read")
    private Boolean notificationRead;
}