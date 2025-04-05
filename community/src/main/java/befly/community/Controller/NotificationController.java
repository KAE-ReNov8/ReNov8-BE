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
    private final KafkaFreeCommentProducerService freeCommentService;

    public NotificationController(KafkaNotificationService notificationService, KafkaFreeCommentProducerService freeCommentService) {
        this.notificationService = notificationService;
        this.freeCommentService = freeCommentService;
    }

    @GetMapping("/noti")
    public SseEmitter streamNotifications() {
        return notificationService.addEmitter(); // Service를 통해 SSE 클라이언트 관리
    }


    /**
     * 카프카 메세지 소비
     * @param record
     */
    @KafkaListener(topics = "free-comment-notifications", groupId = "free-notification-group")
    public void consumeKafkaMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        log.info("Received Kafka Message: {}", message);
        notificationService.sendNotificationToClients(message); // 메시지 전송 로직 위임
    }
}