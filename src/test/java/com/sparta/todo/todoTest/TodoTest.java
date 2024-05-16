package com.sparta.todo.todoTest;

import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @DisplayName("todoList save하기")
    void todoSave() {
        Todo todo = new Todo();
        todo.setContents("할일 목록 1");
        todo.setManager("홍준빈");
        todo.setTitle("todo title");
        todo.setPassword("1234");

        Todo saveTodo = todoRepository.save(todo);
        System.out.println(saveTodo);

    }
}
