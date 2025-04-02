package befly.community.domain.comment;

import befly.common.BaseTime;
import befly.community.domain.FreePost;
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
public class FreeComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_comment_id", nullable = false)
    private Long freeCommentId; // 자유함 댓글 ID (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_id", nullable = false)
    private FreePost freeId; // 댓글이 달린 글 ID (FK)

    @Column(name = "user_id", nullable = false)
    private Long userId; // 댓글 작성자 ID (FK)

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false; // 댓글 삭제 여부

    @Column(name = "free_comment", nullable = false, length = 500)
    private String freeComment; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "p_free_comment_id")
    private FreeComment pFreeCommentId; // 부모 댓글 ID (대댓글)
}
