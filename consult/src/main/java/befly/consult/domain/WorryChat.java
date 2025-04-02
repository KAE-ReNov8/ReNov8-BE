package befly.consult.domain;

import befly.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorryChat extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long worryChatId; // worry_chat_id (PK, AUTO-INCREMENT)

    @JoinColumn(name = "worry_id", nullable = false)
    private Long worryId; // worry_id (FK)

    @Column(name = "user_id")
    private Long userId; // user_id (FK)

    @Column
    private String summary; // summary
}
