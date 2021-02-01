package com.example.todolist.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.todolist.controller.dto.TaskDto;
import com.example.todolist.controller.form.TaskForm;
import com.example.todolist.controller.form.UpdateTaskForm;
import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.ToDoListRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ToDoListRepository toDoListRepository;

	@GetMapping
	public List<TaskDto> listTasks() {
		List<Task> tasks = taskRepository.findAll();
		return TaskDto.converter(tasks);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<TaskDto> insertTask(@RequestBody @Valid TaskForm form, UriComponentsBuilder uriBuilder) {
		Task t = form.converter(toDoListRepository);
		taskRepository.save(t);
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(t));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody @Valid UpdateTaskForm form) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			Task task = form.update(id, taskRepository);
			return ResponseEntity.ok(new TaskDto(task));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
