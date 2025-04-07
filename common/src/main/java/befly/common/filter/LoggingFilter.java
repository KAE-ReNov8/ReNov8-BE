package befly.common.filter;

import befly.common.logging.RequestApiInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        Instant start = Instant.now();
        //cache된 request
        HttpServletRequest requestWrapper = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest();
        // response 상태 추적용 래퍼
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            Instant end = Instant.now();
            long duration = Duration.between(start, end).toMillis();

            try {
                RequestApiInfoDto requestApiInfoDto =
                        RequestApiInfoDto.of(requestWrapper, responseWrapper.getStatus(), duration);
                System.out.println("REQUEST LOG: " + objectMapper.writeValueAsString(requestApiInfoDto));
            } catch (IOException e) {
                logger.warn("Failed to log request info", e);
            }
            responseWrapper.copyBodyToResponse();
        }
    }
}

