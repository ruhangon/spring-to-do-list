package com.example.todolist.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskForm {
	@NotBlank
	@Size(min = 1, max = 100, message = "info must be between 1 and 100 characters")
	private String info;
	@NotNull(message = "target_date can not be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message = "date is invalid")
	private LocalDate targetDate;

	public Task update(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.getOne(id);
		task.setInfo(this.info);
		task.setTargetDate(this.targetDate);
		return task;
	}

}
