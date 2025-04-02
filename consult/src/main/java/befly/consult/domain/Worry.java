package befly.consult.domain;

import befly.consult.domain.Enum.WorryState;
import befly.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worry")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Worry extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worry_id", nullable = false, updatable = false)
    private Long worryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "worry_state", nullable = false)
    private WorryState worryState;

    @Column(name = "worry_title", nullable = false, length = 255)
    private String worryTitle;

    @Column(name = "worry_content", nullable = false, columnDefinition = "TEXT")
    private String worryContent;
}
