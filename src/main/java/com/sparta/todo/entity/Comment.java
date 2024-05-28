package com.sparta.todo.entity;

import com.sparta.todo.dto.CommentSaveRequestDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "comment")
@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contents;

    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(Todo todo) {
        this.todo = todo;
    }

    public Comment(CommentSaveRequestDto requestDto, Todo todo) {
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.todo = todo;
    }

    public void update(CommentUpdateRequestDto requestDto, Todo todo) {
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.todo = todo;
    }
}
