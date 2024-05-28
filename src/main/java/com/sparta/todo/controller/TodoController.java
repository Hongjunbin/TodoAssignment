package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.exception.PasswordException;
import com.sparta.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * 1단계 : 일정의 정보를 저장하는 API
     * @param requestDto : 저장할 일정의 정보
     * @return : 저장된 일정 정보
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.saveTodo(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 2단계 : 선택한 일정의 정보를 조회하는 API
     * @param id : 선택한 일정의 id
     * @return : 선택한 일정의 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodo(@PathVariable("id") Long id) {
        try {
            TodoResponseDto responseDto = todoService.findTodo(id);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(DataNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 3단계 : 등록된 일정 전체를 조회하는 API
     * @return : 등록된 모든 일정의 정보
     */
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAllTodo() {
        try {
            List<TodoResponseDto> responseDto = todoService.findAllTodo();
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 4단계 : 선택한 일정의 할일 제목, 할일 내용, 담당자 수정 API ( 조건 : 비밀번호 함께 전달 )
     * @param id : 선택한 일정의 id
     * @param requestDto : 수정할 제목, 내용, 담당자 정보
     * @return : 수정된 일정의 정보 반환
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoRequestDto requestDto) {
        try {
            TodoResponseDto responseDto = todoService.updateTodo(id, requestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(PasswordException | DataNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 5단계 : 선택한 일정 삭제 API
     * @param id : 삭제할 일정의 id
     * @param requestDto : 삭제할 일정의 비밀번호
     * @return : 삭제된 일정의 id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") Long id, @RequestBody TodoRequestDto requestDto) {
        // 글로벌 익셉션 핸들러
        try {
            todoService.deleteTodo(id, requestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(PasswordException | DataNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}