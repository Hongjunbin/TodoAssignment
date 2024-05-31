package com.sparta.todo.dto;

import com.sparta.todo.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveRequestDto {

    @NotNull(message = "일정의 ID가 필요합니다.")
    private Long todoId;

    @NotEmpty(message = "댓글의 내용이 비어있습니다.")
    private String contents; // 댓글

    private User user;
}
