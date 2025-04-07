package befly.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import befly.common.apiPayload.ApiResponse;
import befly.common.code.status.SuccessStatus;
import befly.user.domain.User;
import befly.user.dto.SignInRequest;
import befly.user.dto.SignUpRequest;
import befly.user.service.EmailDuplication;
import befly.user.service.SignInService;
import befly.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class BeflyLoginController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final EmailDuplication emailDuplication;

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


//    TODO 추후 jwt 토큰 리턴 형태로?? 로직 변경 필요
    @PostMapping("/signin")
    public String signIn(@RequestBody SignInRequest signInRequest) {
        log.info("signIn Request : {}", signInRequest);
        String token = signInService.signIn(signInRequest);
        log.info("signIn Success");
        return token;
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
}
