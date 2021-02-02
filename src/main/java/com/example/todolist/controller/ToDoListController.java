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

import com.example.todolist.controller.dto.ToDoListDto;
import com.example.todolist.controller.form.ToDoListForm;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.ToDoListRepository;

@RestController
@RequestMapping("/todolists")
public class ToDoListController {
	@Autowired
	private ToDoListRepository toDoListRepository;

	@GetMapping
	public List<ToDoListDto> listToDoLists() {
		List<ToDoList> toDoLists = toDoListRepository.findAll();
		return ToDoListDto.converter(toDoLists);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ToDoListDto> detailToDoList(@PathVariable Long id) {
		Optional<ToDoList> toDoList = toDoListRepository.findById(id);
		if (toDoList.isPresent()) {
			return ResponseEntity.ok(new ToDoListDto(toDoList.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping
	public ResponseEntity<ToDoListDto> insertToDoList(@RequestBody @Valid ToDoListForm form,
			UriComponentsBuilder uriBuilder) {
		ToDoList t = form.converter();
		toDoListRepository.save(t);
		URI uri = uriBuilder.path("/todolists/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).body(new ToDoListDto(t));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ToDoListDto> updateToDoList(@PathVariable Long id, @RequestBody @Valid ToDoListForm form) {
		Optional<ToDoList> optional = toDoListRepository.findById(id);
		if (optional.isPresent()) {
			ToDoList toDoList = form.update(id, toDoListRepository);
			return ResponseEntity.ok(new ToDoListDto(toDoList));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteToDoList(@PathVariable Long id) {
		Optional<ToDoList> optional = toDoListRepository.findById(id);
		if (optional.isPresent()) {
			toDoListRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
