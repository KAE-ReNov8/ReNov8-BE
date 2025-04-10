package befly.user.controller;

import befly.common.apiPayload.ApiResponse;
import befly.common.code.status.SuccessStatus;
import befly.user.domain.User;
import befly.user.dto.*;
import befly.user.service.EmailDuplication;
import befly.user.service.GateWayService;
import befly.user.service.SignInService;
import befly.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class BeflyLoginController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final EmailDuplication emailDuplication;
    private final GateWayService gateWayService;


    /**
     * 회원가입 로직
     * @param signUpRequest 실명, 닉네임, 이메일, 패스워드 받음
     * TODO 추후 profile img를 url로 저장할 수 있도록 로직 수정 필요
     * @return 아직 없음
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("signUp Request : {}", signUpRequest);
        User signUpUser = signUpService.signUp(signUpRequest);
        log.info("signUp Success : {}", signUpUser);

        ApiResponse<String> response = ApiResponse.onSuccess("회원가입이 완료되었습니다.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signin")
    public ApiResponse<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        log.info("signIn Request : {}", signInRequest);
        ApiResponse<TokenResponse> apiResponse = signInService.signIn(signInRequest);
        log.info("signIn Success");
        return apiResponse;
    }


    /**
     * 이메일 중복 체크
     * @param Email 가입 ID 겸 이메일
     * @return 함수 반환값은 항상 True. 만약 중복 발생 시 서비스에서 예외 던짐
     */
    @GetMapping("/email/duplication")
    public String checkNicknameDuplication(@RequestParam String Email) {
        log.info("Email duplication check: {}", Email);
        if(!emailDuplication.isDuplication(Email)) {
            log.info("Email duplication check success: {}, No email Duplication", Email);
        }
        return SuccessStatus._OK.getMessage();
    }



    @PostMapping("/oauth2")
    public LoginResponse oauth2(@RequestBody SocialIdRequest socialIdRequest) {
        log.info("SocialId Request : {}", socialIdRequest.getOuath2Id());
        return gateWayService.findUserBySocialId(socialIdRequest.getOuath2Id());
    }

    @GetMapping("/refresh")
    public LoginResponse refreshToken(@RequestHeader("userId") long userId) {
        log.info("Refresh Token Request : {}", userId);
        return gateWayService.generateLoginResponse(userId);
    }
}
