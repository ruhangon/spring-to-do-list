package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
	ToDoList findByName(String name);

}
