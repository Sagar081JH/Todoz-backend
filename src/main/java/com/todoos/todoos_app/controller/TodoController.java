package com.todoos.todoos_app.controller;

import com.todoos.todoos_app.model.Todo;
import com.todoos.todoos_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    String homepage() {
        return "This is home page";
    }

    @GetMapping("/todos/{id}")
    ResponseEntity<Optional<Todo>> getTodoById(@PathVariable int id){
        Optional<Todo> todo = Optional.empty();
        try{
            todo = todoService.getTodoById(id);
        }catch (Exception e){
            System.out.println(e + ": Failed to load todo with id "+id);
        }
        if(todo.isPresent()){
            return new ResponseEntity<>(todo,HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = Collections.emptyList();
        try{
            todos = todoService.getAllTodos();
        }catch (Exception e){
            System.out.println(e + ": Failed to load todos");
        }
        if(!todos.isEmpty()){
            return new ResponseEntity<>(todos,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        Todo todo1 = null;
        try{
            todo1 = todoService.addTodo(todo);
        }catch (Exception e){
            System.out.println(e + ": Failed to add todo : "+ todo);
        }

        if(todo1 != null){
            return new ResponseEntity<>(todo1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
    }
    @PutMapping("todos/update/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable int id, @RequestBody Todo todo){
        Todo respTodo = null;
        try {
            respTodo = todoService.updateTodoById(id, todo);
        }catch(Exception e){
            System.out.println(e + ": Failed to update todo with id : "+ id);
        }
        if(respTodo != null){
            return new ResponseEntity<>(respTodo,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("todos/delete/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable int id){
        if(todoService.deleteTodoById(id)){
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
