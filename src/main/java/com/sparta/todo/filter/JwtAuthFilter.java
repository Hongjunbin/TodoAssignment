package com.sparta.todo.filter;

import com.sparta.todo.jwt.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "JwtAuthFilter")
@Component
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader(JwtUtil.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(url) && (url.startsWith("/api/comments"))) {
            if(!StringUtils.hasText(token)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.getWriter().write("not token get out");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}