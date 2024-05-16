package com.sparta.todo.repository;

import com.sparta.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // JpaRepository => Entity, id
}
// spring 시작될때 => simpleRepository가 TodoRepository를 읽는다. 
// 구현체 생성