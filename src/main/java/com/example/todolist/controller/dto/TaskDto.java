package com.example.todolist.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.todolist.model.Task;

import lombok.Getter;

@Getter
public class TaskDto {
	private Long id;
	private String info;
	private LocalDate targetDate;

	public TaskDto(Task task) {
		this.id = task.getId();
		this.info = task.getInfo();
		this.targetDate = task.getTargetDate();
	}

	public static List<TaskDto> converter(List<Task> tasks) {
		return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
	}

}
