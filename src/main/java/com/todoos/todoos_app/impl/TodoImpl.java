package com.todoos.todoos_app.impl;

import com.todoos.todoos_app.model.Todo;
import com.todoos.todoos_app.repo.TodoRepo;
import com.todoos.todoos_app.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoImpl implements TodoService{

    @Autowired
    TodoRepo todoRepo;

    @Override
    public  Optional<Todo> getTodoById(int id){
        if(todoRepo.existsById(id)){
            return todoRepo.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Todo> getAllTodos(){
        List<Todo> todoList = new ArrayList<>();
        todoRepo.findAll().forEach(todoList :: add);
        return todoList;
    }

    @Override
    public Todo addTodo(Todo todo){
        if(!todoRepo.existsById(todo.getTodoId())){
            todoRepo.save(todo);
        }
        return todo;
    }

    @Override
    public Todo updateTodoById(int id,Todo todo){
        if(todoRepo.existsById(id)){
            todoRepo.save(todo);
            return todo;
        }
        return todo;
    }

    @Override
    public boolean deleteTodoById(int id){
        boolean isDeleted = false;
            try{
                if(todoRepo.existsById(id)) {
                    todoRepo.deleteById(id);
                    isDeleted = true;
                }
            }catch (Exception e) {
                System.out.println(e + ": Failed to delete todo with id : " + id);
                return isDeleted;
            }
        return isDeleted;
    }
}
