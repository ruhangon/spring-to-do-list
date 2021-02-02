package com.example.todolist.controller.dto;

import java.time.LocalDate;

import com.example.todolist.model.Task;

import lombok.Getter;

@Getter
public class DetailedTaskDto {
	private Long id;
	private String info;
	private LocalDate targetDate;
	private String nameToDoList;

	public DetailedTaskDto(Task task) {
		this.id = task.getId();
		this.info = task.getInfo();
		this.targetDate = task.getTargetDate();
		this.nameToDoList = task.getToDoList().getName();
	}

}
