package com.sparta.todo.controller;

import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.dto.CommentSaveRequestDto;
import com.sparta.todo.dto.CommentUpdateRequestDto;
import com.sparta.todo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 2단계 : 댓글 등록
     * @param requestDto : todoId & 댓글 등록 정보
     * @return : 등록에 성공한 댓글 정보
     */
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody @Valid CommentSaveRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.saveComment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 3단계 : 댓글 수정 ( 댓글의 선택한 사용자와 변경하는 현재 사용자의 정보 일치 여부 판단 )
     * @param commentId : 현재 변경할 댓글의 id
     * @param requestDto : 현재 변경할 댓글의 id & 수정할 댓글의 정보
     * @return : 수정된 댓글의 정보
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentUpdateRequestDto requestDto)
    {
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto requestDto)
    {
        commentService.deleteComment(commentId, requestDto);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }
}
