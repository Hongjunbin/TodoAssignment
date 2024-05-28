package com.sparta.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotNull(message = "일정의 ID가 필요합니다.")
    private Long todoId;

    @NotNull(message = "댓글의 ID가 필요합니다.")
    private Long commentId;

    @NotEmpty(message = "댓글의 내용이 비어있습니다.")
    private String contents; // 댓글
    private String username; // 사용자 아이디
}
