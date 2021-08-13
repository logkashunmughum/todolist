package com.deloitte.todolist.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.todolist.application.model.ResponseModel;
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
	
	@Autowired
	private ResponseModel responseModel;

	@GetMapping("/getTodo")
	public List<TodoItems> getTodoList() {
		return todoRepo.findAll();
	}
	

	@GetMapping("/getTodoById/{id}")
	public Optional<TodoItems> getTodo(@PathVariable Long id) {
		return todoRepo.findById(id);
	}
	
	@PostMapping("/saveTodo")
	public ResponseEntity<ResponseModel> saveTodo(@RequestBody @NotNull TodoItems todoItem) {
		todoRepo.save(todoItem);
		responseModel.setMessage("Todo added successfully");
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}
	
	@PutMapping("/updateTodo/{id}")
	public ResponseEntity<ResponseModel> updateTodo(@RequestBody @NotNull TodoItems todoItem,
			@PathVariable long id) {
		todoItem.setId(id);
		todoRepo.saveAndFlush(todoItem);
		responseModel.setMessage("Todo updated successfully");
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeTodo/{id}")
	public ResponseEntity<ResponseModel> removeTodo(@PathVariable Long id) {
		todoRepo.deleteById(id);
		responseModel.setMessage("Todo Deleted successfully");
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}
	
	
	@GetMapping("/login")
	public Boolean checkLogin(@RequestHeader String userName, @RequestHeader String password) {
		return todoService.checkUserLogin(userName, password);
	}
}
