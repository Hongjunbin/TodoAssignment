package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        Todo saveTodo = todoRepository.save(todo);
        TodoResponseDto responseDto = new TodoResponseDto(saveTodo);
        return responseDto;
    }

    public TodoResponseDto findTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(NullPointerException::new);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return responseDto;
    }

    public List<TodoResponseDto> findAllTodo() {
        List<TodoResponseDto> responseDto = todoRepository.findAllByOrderByCreateDateDesc()
                .stream().map(TodoResponseDto::new).toList();
        return responseDto;
    }

}
