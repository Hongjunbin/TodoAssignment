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

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentSaveRequestDto requestDto, Todo todo, User user) {
        this.contents = requestDto.getContents();
        this.todo = todo;
        this.user = user;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
