package befly.common.logging;

import befly.common.wapper.CachingBodyRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record RequestApiInfoDto(
        String url,
        String methodName,
        Map<String, String> header,
        Object parameters,
        Object body,
        String ipAddress,
        String timestamp,
        int httpStatus,
        Long duration   //ms 단위
) {
    //생성자
    public static RequestApiInfoDto of(HttpServletRequest request,
                                       int httpStatus,
                                       long duration) throws IOException {
        Object body;
        if (request instanceof CachingBodyRequestWrapper wrapper) {
            body = wrapper.getInputStream();
        } else if (request instanceof ContentCachingRequestWrapper wrapper) {
            body = wrapper.getInputStream();
        } else {
            body = null; // 캐싱된 바디를 가져올 수 없음
        }

        return new RequestApiInfoDto(
                request.getRequestURI(),
                request.getMethod(),
                extractEssentialHeaders(request),
                request.getParameterMap(),
                body,
                request.getRemoteAddr(),
                LocalDateTime.now().toString(),
                httpStatus,
                duration
        );
    }

    private static Map<String, String> extractEssentialHeaders(HttpServletRequest request) {
        List<String> essentialKeys = List.of("user-agent", "host", "content-type", "x-forwarded-for");
        Map<String, String> filteredHeaders = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (essentialKeys.contains(headerName.toLowerCase())) {
                filteredHeaders.put(headerName, request.getHeader(headerName));
            }
        }
        return filteredHeaders;
    }
}
