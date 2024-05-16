package com.sparta.todo.entity;

import com.sparta.todo.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "manager")
    private String manager;

    @Column(name = "password")
    private String password;

    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
    }

}
// @Entity // 이 클래스를 테이블처럼 역할을 하겠다 지정.
// @Id : table 식별값 @Id만 붙히면 따로 값을 넣어줘야한다. 안넣어주면 오류가 난다! 식별값
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// 옵션없으면 직접 id에 식별값을 부여해야한다. mysql의 시퀀스 만드는 옵션