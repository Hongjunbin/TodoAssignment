package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * 1단계 : 일정의 정보를 저장하는 API
     * @param requestDto : 저장할 일정의 정보
     * @return : 저장된 일정 정보
     */
    @PostMapping("/todo")
    public ResponseEntity<Object> saveTodo(@RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.saveTodo(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 2단계 : 선택한 일정의 정보를 조회하는 API
     * @param id : 선택한 일정의 id
     * @return : 선택한 일정의 정보
     */
    @GetMapping("/todo/{id}")
    public ResponseEntity<Object> findTodo(@PathVariable("id") Long id) {
        TodoResponseDto responseDto = todoService.findTodo(id);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 3단계 : 등록된 일정 전체를 조회하는 API
     * @return : 등록된 모든 일정의 정보
     */
    @GetMapping("/todo")
    public ResponseEntity<Object> findAllTodo() {
        List<TodoResponseDto> responseDto = todoService.findAllTodo();
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 4단계 : 선택한 일정의 할일 제목, 할일 내용, 담당자 수정 API ( 조건 : 비밀번호 함께 전달 )
     * @param id : 선택한 일정의 id
     * @param requestDto : 수정할 제목, 내용, 담당자 정보
     * @return : 수정된 일정의 정보 반환
     */
    @PutMapping("/todo/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable("id") Long id, @RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.updateTodo(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 5단계 : 선택한 일정 삭제 API
     * @param id : 삭제할 일정의 id
     * @param requestDto : 삭제할 일정의 비밀번호
     * @return : 삭제된 일정의 id
     */
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable("id") Long id, @RequestBody TodoRequestDto requestDto) {
        Long deleteId = todoService.deleteTodo(id, requestDto);
        return ResponseEntity.ok(deleteId);
    }


}