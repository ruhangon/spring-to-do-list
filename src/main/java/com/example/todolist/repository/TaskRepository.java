package com.example.todolist.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByTargetDate(LocalDate targetDate);

}
