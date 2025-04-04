package befly.common.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
public record BaseCodeDto (
        HttpStatus httpStatus,
        boolean isSuccess,
        String code,
        String message
) {}
