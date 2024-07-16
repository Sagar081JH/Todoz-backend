package com.todoos.todoos_app.service;

import com.todoos.todoos_app.model.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoService {

    Optional<Todo> getTodoById(int id);

    List<Todo> getAllTodos();

    Todo addTodo(Todo todo);

    Todo updateTodoById(int id,Todo todo);

    boolean deleteTodoById(int id);
}
