package com.sparta.todo.exception;

public class SelectNullException extends Exception {

    public SelectNullException() {
        super("조회된 값이 없습니다.");
    }
}
