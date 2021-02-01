package com.example.todolist.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.ToDoListRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoListForm {
	@NotBlank
	@Size(min = 1, max = 20, message = "name must be between 1 and 20 characters")
	private String name;
	@NotBlank
	@Size(min = 1, max = 100, message = "description must be between 1 and 100 characters")
	private String description;

	public ToDoList converter() {
		ToDoList t = new ToDoList(name, description);
		return t;
	}

	public ToDoList update(Long id, ToDoListRepository toDoListRepository) {
		ToDoList toDoList = toDoListRepository.getOne(id);
		toDoList.setName(this.name);
		toDoList.setDescription(this.description);
		return toDoList;
	}

}
