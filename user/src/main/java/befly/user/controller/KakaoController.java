package befly.user.controller;

import befly.user.dto.KakaoUserResponse;
import befly.user.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoAuthService kakaoAuthService;
    @GetMapping("/token")
    public KakaoUserResponse kakaoLogin(@RequestParam("code") String code,
                                        @RequestParam("state") String state) {
        //String accessToken = kakaoAuthService.getAccessToken(code);
        //return kakaoAuthService.getUserInfo(accessToken);
        return null;
    }
}
