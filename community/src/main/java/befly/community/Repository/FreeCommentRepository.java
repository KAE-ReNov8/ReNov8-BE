package befly.community.Repository;

import befly.community.domain.comment.FreeComment;
import org.springframework.data.jpa.repository.JpaRepository;

   public interface FreeCommentRepository extends JpaRepository<FreeComment, Long> {
   }