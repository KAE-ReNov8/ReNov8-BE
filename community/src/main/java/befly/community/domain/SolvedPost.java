package befly.community.domain;

import befly.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolvedPost extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solved_id")
    private Long solvedId; // Primary Key, Auto-Increment

    @Column(name = "user_id", nullable = false)
    private Long userId; // Foreign Key, Not Null

    @Column(name = "solved_title", nullable = false, length = 255)
    private String solvedTitle; // Not Null

    @Column(name = "solved_content", length = 2048)
    private String solvedContent; // Nullable
}
