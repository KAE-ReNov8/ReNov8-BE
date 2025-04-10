package befly.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    Boolean signUpStatus;
    String accessToken;
    String refreshToken;
}
