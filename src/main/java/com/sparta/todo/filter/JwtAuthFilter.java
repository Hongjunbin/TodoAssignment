package com.sparta.todo.filter;

import com.sparta.todo.entity.User;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRespository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "JwtAuthFilter")
@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserRespository userRespository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURI();
        String token = jwtUtil.getJwtFromHeader(httpServletRequest);

        if (StringUtils.hasText(url) && (url.startsWith("/api/comments"))) {
            if(!StringUtils.hasText(token)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.getWriter().write("not token get out");
            } else {
                if(jwtUtil.validateToken(token)) {
                    Claims tokenInfo = jwtUtil.getUserInfoFromToken(token);
                    String username = tokenInfo.getSubject();

                    User user = userRespository.findByUsername(username).orElseThrow(
                            () -> new DataNotFoundException("no data")
                    );

                    request.setAttribute("user", user);
                    filterChain.doFilter(request, response);
                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.getWriter().write("token no check get out");
                }
            }
            return;
        }
        filterChain.doFilter(request, response);
    }
}