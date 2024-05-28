package com.sparta.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 일정이 DB에 저장되지 않은 경우
     * @param ex : 해당 예외 메시지
     * @return : NOT_FOUND(404)
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Void> selectNullException(DataNotFoundException ex) {
        log.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * 현재 사용자와 변경할 사용자의 일치 여부
     * @param ex : 해당 예외 메시지
     * @return : NOT_FOUND(404)
     */
    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<Void> notMatchException(NotMatchException ex) {
        log.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
