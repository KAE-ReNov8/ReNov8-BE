package befly.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 공통 필드를 상속받는 클래스에 매핑
public abstract class BaseTime {

    @Column(nullable = false, updatable = false) // 생성 시에만 값 설정 가능
    private LocalDateTime createdAt; // created_at

    @Column // 수정 시 자동 업데이트
    private LocalDateTime updatedAt; // updated_at

    @PrePersist // 엔티티 저장 전에 실행
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate // 엔티티 수정 전에 실행
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}