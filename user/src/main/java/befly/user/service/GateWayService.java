package befly.user.service;

import befly.user.config.JwtProvider;
import befly.user.dto.LoginResponse;
import befly.user.repository.UserRepository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class GateWayService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public LoginResponse findUserBySocialId(String socialId) {
        return userRepository.findByEmail(socialId)
                .map(user -> createTokenResponse(user.getUserId()))
                .orElseGet(() -> buildLoginResponse(false, null, null));
    }


    public LoginResponse generateLoginResponse(long userId) {
        if (isUserExists(userId)) {
            return createTokenResponse(userId);
        } else {
            return buildLoginResponse(false, null, null);
        }
    }

    public boolean isUserExists(long userId) {
        return userRepository.findById(userId).isPresent();
    }

    private LoginResponse createTokenResponse(Long userId) {
        String id = userId.toString();
        String accessToken = jwtProvider.generateAccessToken(id);
        String refreshToken = jwtProvider.generateRefreshToken(id);
        return buildLoginResponse(true, accessToken, refreshToken);
    }

    private LoginResponse buildLoginResponse(boolean signUpStatus, String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .signUpStatus(signUpStatus)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}