package befly.community.Service.kafka;

import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class KafkaFreeCommentProducerService {

    private static final String TOPIC = "free-comment-notifications";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaFreeCommentProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    /**
     * FIXME
     * 아직 해당 함수가 누구에게 알림이 가는지 확인 필요. localhost상으로 스스로에게 메세지 발행 및 소비 가능
     * 댓글 작성 시 해당 함수 호출하면 알림이 감
     * @param comment 작성한 댓글 내용
     * @param userId 작성한 유저의 userID
     */
    public void addComment(String comment, Long userId) {
        /**
         * FIXME 중요!!!
         * MSA 방식으로 수정 필요.
         * 해당 코드가 살아있을 시, DB 설계 상 돌아가지 않으니, 임시로 주석 처리
         */
//        FreeComment freeComment = FreeComment.builder()
//                .freeComment(comment)
//                .userId(userId)
//                .build();
//        freeCommentRepository.save(freeComment);

        /**
         * FIXME
         * 추후 userID가 아닌 닉네임으로 수정 필요.
         */
        String message = userId + "님이 자유함(Worry)에 댓글을 달았습니다.";
        kafkaTemplate.send(TOPIC, message);
    }
}