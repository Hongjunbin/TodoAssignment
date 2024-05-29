package com.sparta.todo.controller;

import com.sparta.todo.entity.UserRoleEnum;
import com.sparta.todo.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class JwtController {

    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Void> createAuth(HttpServletResponse response) {
        String token = jwtUtil.createToken("Junbin", UserRoleEnum.USER);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        log.info("log : {}", token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Void> test(HttpServletRequest request , HttpServletResponse response) {
        String header = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        log.info("header : {}", header);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
