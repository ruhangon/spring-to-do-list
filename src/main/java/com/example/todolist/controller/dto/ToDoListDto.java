package com.example.todolist.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.todolist.model.ToDoList;

import lombok.Getter;

@Getter
public class ToDoListDto {
	private Long id;
	private String name;
	private String description;

	public ToDoListDto(ToDoList toDoList) {
		this.id = toDoList.getId();
		this.name = toDoList.getName();
		this.description = toDoList.getDescription();
	}

	public static List<ToDoListDto> converter(List<ToDoList> toDoLists) {
		return toDoLists.stream().map(ToDoListDto::new).collect(Collectors.toList());
	}

}
