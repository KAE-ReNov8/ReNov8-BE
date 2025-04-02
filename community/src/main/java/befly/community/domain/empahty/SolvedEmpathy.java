package befly.community.domain.empahty;

import befly.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solved_empathy")
public class SolvedEmpathy extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solved_empathy_id")
    private Long solvedEmpathyId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "solved_id", nullable = false)
    private Long solvedId;

}
