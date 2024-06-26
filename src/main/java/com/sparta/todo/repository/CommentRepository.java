package com.sparta.todo.repository;

import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
