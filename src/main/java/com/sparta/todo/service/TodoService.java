package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.exception.PasswordException;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        Todo saveTodo = todoRepository.save(todo);
        TodoResponseDto responseDto = new TodoResponseDto(saveTodo);
        return responseDto;
    }

    public TodoResponseDto findTodo(Long id) throws DataNotFoundException {
        Todo todo = findByTodoId(id);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return responseDto;
    }

    public List<TodoResponseDto> findAllTodo() {
        List<TodoResponseDto> responseDto = todoRepository.findAllByOrderByCreateDateDesc()
                .stream().map(TodoResponseDto::new).toList();
        return responseDto;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) throws PasswordException, DataNotFoundException {
        Todo todo = findByTodoId(id);
        if(requestDto.getPassword().equals(todo.getPassword())) {
            todo.update(requestDto);
        } else {
            throw new PasswordException("비밀번호가 일치하지 않습니다!");
        }
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return responseDto;
    }

    public void deleteTodo(Long id, TodoRequestDto requestDto) throws PasswordException, DataNotFoundException {
        Todo todo = findByTodoId(id);
        if(requestDto.getPassword().equals(todo.getPassword())) {
            todoRepository.delete(todo);
        } else {
            throw new PasswordException("비밀번호가 일치하지 않습니다!");
        }
    }

    private Todo findByTodoId(Long id) throws DataNotFoundException {
        return todoRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("잘못됨")
        );
    }

}
