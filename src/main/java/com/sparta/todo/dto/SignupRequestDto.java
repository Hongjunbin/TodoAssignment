package com.sparta.todo.dto;

import com.sparta.todo.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String nickname;

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9]{8,15}$")
    private String password;

    private final UserRoleEnum role = UserRoleEnum.USER;
}
