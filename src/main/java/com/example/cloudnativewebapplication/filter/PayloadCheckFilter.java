package com.example.cloudnativewebapplication.filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class PayloadCheckFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(PayloadCheckFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("in the first filter");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        log.info("PayloadCheckFilter invoked for URI: {}", httpRequest.getRequestURI());

        // Check for content length or use httpRequest.getInputStream() to check for content
        if (httpRequest.getContentLength() > 0) {
            log.warn("Request to {} contains payload. Rejecting with 400 Bad Request.", httpRequest.getRequestURI());
            // If content is present, return a 400 Bad Request and do not continue the filter chain
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            // Ensure no response body is sent
            httpResponse.getWriter().close();
        } else {
            // If no content, continue to the next element in the filter chain
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}