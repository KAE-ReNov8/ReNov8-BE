package befly.community.domain.comment;


import befly.common.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolvedComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solvedCommentId; // solved_comment_id (PK)

    @Column(nullable = false)
    private Long solvedId; // solved_id (FK)

    @Column(nullable = false)
    private Long userId; // user_id (FK)

    @Column(nullable = false, length = 255)
    private String solvedComment; // solved_comment (NOT NULL)

    @Column
    private Long pSolvedCommentId; // p_solved_comment_id

    @Column(nullable = false)
    private Boolean isDeleted = Boolean.FALSE; // is_deleted (default = FALSE)
}
