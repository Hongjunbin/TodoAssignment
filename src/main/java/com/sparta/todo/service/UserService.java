package com.sparta.todo.service;

import com.sparta.todo.dto.LoginRequestDto;
import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.entity.UserRoleEnum;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRespository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto requestDto) {
        boolean isUsername = userRespository.findByUsername(requestDto.getUsername()).isPresent();

        if(!isUsername) {
            User user = new User(requestDto);
            userRespository.save(user);
        } else {
            throw new DataNotFoundException("중복된 아이디 입니다.");
        }
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse response) {

        User user = userRespository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new DataNotFoundException("존재하지 않는 아이디 입니다.")
        );

        String token = jwtUtil.createToken(user.getUsername(), UserRoleEnum.USER);
        jwtUtil.addJwtToHeader(token, response);
    }
}
