package befly.common.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 공통 필드를 상속받는 클래스에 매핑
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt; // created_at

    @LastModifiedDate
    private LocalDateTime updatedAt; // updated_at
}