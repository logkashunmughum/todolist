package com.deloitte.todolist.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.todolist.application.model.TodoItems;
import com.deloitte.todolist.application.repository.TodoRepository;
import com.deloitte.todolist.application.service.TodoService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private TodoService todoService;

	@GetMapping("/getTodo")
	public List<TodoItems> getTodoList() {
		return todoRepo.findAll();
	}
	
	@PostMapping("/saveTodo")
	public String saveTodo(@Valid @RequestBody @NotNull TodoItems todoItem) {
		todoRepo.save(todoItem);
		return "Todo added successfully";
	}
	
	@PutMapping("/updateTodo")
	public String updateTodo(@Valid @RequestBody @NotNull TodoItems todoItem) {
		todoRepo.save(todoItem);
		return "Todo updated Successfully";
	}
	
	@DeleteMapping("/removeTodo/{id}")
	public String removeTodo(@PathVariable Long id) {
		todoRepo.deleteById(id);
		return "Todo removed successfully";
	}
	
	@GetMapping("/login")
	public Boolean checkLogin(@RequestHeader String userName, @RequestHeader String password) {
		return todoService.checkUserLogin(userName, password);
	}
}
