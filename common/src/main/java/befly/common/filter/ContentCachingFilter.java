package befly.common.filter;

import befly.common.wapper.CachingBodyRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
public class ContentCachingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(isMultipartRequest(request)) {   //multipart
            CachingBodyRequestWrapper requestWrapper = new CachingBodyRequestWrapper(request);
            filterChain.doFilter(requestWrapper, response);
        }else {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            filterChain.doFilter(requestWrapper, response);
        }
    }

    //multipart 요청(사진) 판단
    private boolean isMultipartRequest(HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/");
    }
}
