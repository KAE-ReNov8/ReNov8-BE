package befly.user.service;

import befly.common.apiPayload.ApiResponse;
import befly.common.code.status.GlobalErrorStatus;
import befly.common.exception.RestApiException;
import befly.user.config.JwtProvider;
import befly.user.domain.User;
import befly.user.dto.SignInRequest;
import befly.user.dto.TokenResponse;
import befly.user.repository.UserRepository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SignInService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository; // Repository 추가
    private final JwtProvider jwtProvider;


    public ApiResponse<TokenResponse> signIn(SignInRequest signInRequest) {
        log.info("SignIn request started for email: {}", signInRequest.getEmail());

        // 1. User 정보 조회
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new RestApiException(GlobalErrorStatus.MEMBER_NOT_FOUND));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new RestApiException(GlobalErrorStatus.PWD_INVALID);
        }
        log.info("SignIn completed for email: {}", signInRequest.getEmail());

        // 3. JWT 토큰 생성 및 반환
        String accessToken = jwtProvider.generateAccessToken(user.getUserId().toString());
        String refreshToken = jwtProvider.generateRefreshToken(user.getUserId().toString());
        TokenResponse tokenResponse = makeTokenObject(accessToken, refreshToken);
        return ApiResponse.onSuccess(tokenResponse);
    }

    private static TokenResponse makeTokenObject(String accessToken, String refreshToken) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }
}