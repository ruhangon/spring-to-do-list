package com.example.todolist.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FormDtoError {
	private String field;
	private String error;

}
