package befly.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/*
    Gateway에서 인증되어 들어온 요청에는 X-USER-ID가 있음
    그것을 ArgumentResolver에서 사용할 수 있도록 추출

    로그인이 필요한 서비스에 한에 적용 되어야함.
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("X-USER-ID");
        request.setAttribute("userId", userId); // ArgumentResolver에서 꺼내 쓸 수 있게 저장
        return true;
    }
}
