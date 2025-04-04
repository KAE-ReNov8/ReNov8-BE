package befly.user.service;

import befly.user.client.KakaoAuthClient;
import befly.user.client.KakaoUserClient;
import befly.user.dto.KakaoTokenResponse;
import befly.user.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;

    public String getAccessToken(String code) {
        KakaoTokenResponse response = kakaoAuthClient.getKakaoAccessToken(
            "authorization_code",
            "732935614e46722be3537af517f72689",       // 카카오 개발자 콘솔에서 발급받은 클라이언트 ID
            "5teaK9HRw1Wn1B6oBNx0dkc88Cl5s90H",   // 카카오 클라이언트 시크릿
            code,
            "http://localhost:8080/token"     // 등록한 리다이렉트 URI
        );
        return response.getAccessToken();
    }

    public KakaoUserResponse getUserInfo(String accessToken) {
        return kakaoUserClient.getUserInfo("Bearer " + accessToken);
    }
}
