package com.sparta.todo.service;

import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.dto.CommentSaveRequestDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.exception.NotMatchException;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto saveComment(CommentSaveRequestDto requestDto) {
        Todo todo = todoRepository.findById(requestDto.getTodoId()).orElseThrow(
                () -> new DataNotFoundException("조회된 일정이 없습니다.")
        );
        Comment todoResponse = commentRepository.save(new Comment(requestDto, todo));
        return new CommentResponseDto(todoResponse);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        if(!Objects.equals(commentId, requestDto.getCommentId())) {
            throw new NotMatchException();
        }
        Todo todo = todoRepository.findById(requestDto.getTodoId()).orElseThrow(
                () -> new DataNotFoundException("조회된 일정이 없습니다.")
        );
        Comment comment = commentRepository.findById(requestDto.getCommentId()).orElseThrow(
                () -> new DataNotFoundException("조회된 댓글이 없습니다.")
        );
        comment.update(requestDto, todo);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, CommentUpdateRequestDto requestDto) {
        if(!Objects.equals(commentId, requestDto.getCommentId())) {
            throw new NotMatchException();
        }
        if(!(isTodo(requestDto) && isComment(commentId))) {
            throw new DataNotFoundException("조회된 댓글 및 일정이 없습니다.");
        }
        commentRepository.deleteById(requestDto.getCommentId());
    }

    public boolean isTodo(CommentUpdateRequestDto requestDto) {
        return todoRepository.findById(requestDto.getTodoId()).isPresent();
    }
    public boolean isComment(Long commentId) {
        return commentRepository.findById(commentId).isPresent();
    }
}
