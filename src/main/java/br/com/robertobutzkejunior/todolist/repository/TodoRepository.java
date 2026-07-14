package br.com.robertobutzkejunior.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.robertobutzkejunior.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}