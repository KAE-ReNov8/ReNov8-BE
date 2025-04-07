package befly.community.Controller;

import befly.community.Service.kafka.KafkaFreeCommentProducerService;
import befly.community.Service.kafka.KafkaNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
public class NotificationController {

    private final KafkaNotificationService notificationService;

    public NotificationController(KafkaNotificationService notificationService, KafkaFreeCommentProducerService freeCommentService) {
        this.notificationService = notificationService;
    }


    /**
     * 클라이언트로부터 수신한 SSE 요청을 처리합니다.
     * Service를 통해 SSE Emitter를 관리합니다.
     */
    @GetMapping("/noti")
    public SseEmitter streamNotifications() {
        return notificationService.addEmitter(); // Service를 통해 SSE 클라이언트 관리
    }


    /**
     * Kafka로부터 수신한 메시지를 소비하여 클라이언트에게 전달합니다.
     *
     * @param record Kafka에서 받은 메시지를 담고 있는 ConsumerRecord 객체
     */
    @KafkaListener(topics = "free-comment-notifications", groupId = "free-notification-group")
    public void consumeKafkaMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        log.info("Received Kafka Message: {}", message);
        notificationService.sendNotificationToClients(message); // 메시지 전송 로직 위임
    }
}