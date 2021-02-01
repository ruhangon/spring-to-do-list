package com.example.todolist.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.todolist.model.Task;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.ToDoListRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskForm {
	@NotBlank(message = "info can not be blank")
	@Size(min = 1, max = 100, message = "info must be between 1 and 100 characters")
	private String info;
	@NotNull(message = "target_date can not be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message = "date is invalid")
	private LocalDate targetDate;
	@NotEmpty(message = "nameToDoList can not be empty")
	@NotBlank(message = "nameToDoList can not be blank")
	private String nameToDoList;

	public Task converter(ToDoListRepository toDoListRepository) {
		ToDoList toDoList = toDoListRepository.findByName(nameToDoList);
		Task t = new Task(info, targetDate, toDoList);
		return t;
	}

}
