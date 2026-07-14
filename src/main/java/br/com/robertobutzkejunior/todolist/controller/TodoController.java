package br.com.robertobutzkejunior.todolist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.robertobutzkejunior.todolist.entity.Todo;
import br.com.robertobutzkejunior.todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
   private TodoService todoService;
    
   public TodoController(TodoService todoService) {
   	this.todoService = todoService;
}
   
   @PostMapping
   ResponseEntity<List<Todo>> create(@RequestBody Todo todo) {
   	return ResponseEntity.status(HttpStatus.CREATED)
   		.body(todoService.create(todo));
   }
   
   @GetMapping
   List<Todo> list(Todo todo) {
    return todoService.list();
   }

   @PutMapping
   List<Todo> update(@RequestBody Todo todo) {
    return todoService.update(todo);
   }
   
   @DeleteMapping("{id}")
   List<Todo> delete(@PathVariable("id") Long id) {
    return todoService.delete(id);  
   }
    
}
