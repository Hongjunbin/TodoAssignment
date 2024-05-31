package com.sparta.todo.dto;

import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createDate = comment.getCreateDate();
    }
}
