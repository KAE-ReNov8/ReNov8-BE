package befly.community.Service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class KafkaNotificationService {
    // SSE 연결 관리
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // SSE 클라이언트를 추가
    public SseEmitter addEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // 무제한 연결 유지
        emitters.add(emitter);

        // 연결 종료 처리
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    // 메시지를 모든 클라이언트에 전파
    public void sendNotificationToClients(String message) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("message").data(message));
            } catch (IOException e) {
                emitters.remove(emitter); // 실패한 연결 제거
                log.error("Failed to send message to SSE client: {}", e.getMessage());
            }
        }
    }
}