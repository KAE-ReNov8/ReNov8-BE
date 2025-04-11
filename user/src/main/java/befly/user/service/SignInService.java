package befly.user.service;

import befly.common.code.status.GlobalErrorStatus;
import befly.common.exception.RestApiException;
import befly.user.domain.User;
import befly.user.dto.SignInRequest;
import befly.user.repository.userRepository.UserRepository;
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
//    private final JwtTokenProvider jwtTokenProvider; // JWT 제공 클래스 추가

    public String signIn(SignInRequest signInRequest) {
        log.info("SignIn request started for email: {}", signInRequest.getEmail());

        // 1. User 정보 조회
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new RestApiException(GlobalErrorStatus.MEMBER_NOT_FOUND));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new RestApiException(GlobalErrorStatus.PWD_INVALID);
        }
        log.info("SignIn completed for email: {}", signInRequest.getEmail());
//        TODO 추후 로직 수정(JWT?)
        // 3. JWT 토큰 생성 및 반환
//        return jwtTokenProvider.createToken(user.getId(), user.getRoles());
        return "JWT";
    }
}