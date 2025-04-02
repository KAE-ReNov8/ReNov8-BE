package befly.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "worry_chat_session") // MongoDB의 컬렉션 이름
public class Session {
    /** 해당 고민 ID */
    @Field(name = "worry_id")
    private Long worryId;

    /** 해당 유저 ID */
    @Field(name = "user_id")
    private Long userId;

    /** 채팅 리스트 */
    @Field(name = "chat")
    private List<Chat> chat;

    /** 내부 클래스: Chat */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Chat {

        /** 채팅 내용 */
        @Field(name = "message")
        private String message;

        /** 채팅 보낸 시각 */
        @Field(name = "time")
        private LocalDateTime time;

        /** 채팅 보낸 주체 (1: 사용자, 0: AI) */
        @Field(name = "sender")
        private Integer sender; // 1: 유저, 0: AI
    }
}