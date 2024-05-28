package com.sparta.todo.exception;

public class NotMatchException extends RuntimeException {
    public NotMatchException() {
        super("선택한 댓글의 사용자가 현재 사용자와 일치하지 않습니다.");
    }
}
