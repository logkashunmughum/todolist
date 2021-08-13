package com.deloitte.todolist.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deloitte.todolist.application.model.TodoItems;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	 @Autowired
	 MockMvc mvc;
	 
	 @Autowired
	 private ObjectMapper mapper;
	
	 TodoItems todoItems;
	
	

	
	@BeforeEach
	void loadTodo() {
		 todoItems = new TodoItems((long) 1, "Project", true, "complete project by tomorrow");
		 
	}
	 
	@Test
	void getTodoListTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/getTodo");
		MvcResult result = mvc.perform(request).andReturn();
		List<TodoItems> todos = new ArrayList<>();
		assertEquals(todos.size(), result.getResponse().getContentLength());
	}
	
	@Test
	void getUserLoginTest() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/todo/login");
		HttpHeaders header = new HttpHeaders();
		header.add("userName", "test");
		header.add("password", "pwd123");
		request.headers(header);
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals("true", result.getResponse().getContentAsString());
	}
	
	@Test
	void getUserLoginTestForFalse() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/todo/login");
		HttpHeaders header = new HttpHeaders();
		header.add("userName", "test");
		header.add("password", "pwd");
		request.headers(header);
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals("false", result.getResponse().getContentAsString());
	}
	
	@Test
	void getTodoTest() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/todo/getTodo");
		MvcResult result = mvc.perform(request).andReturn();
		assertNotNull(result.getResponse().getContentAsString());
	}
	
	@Test
	void saveTodoTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/todo/saveTodo")
				 .contentType(MediaType.APPLICATION_JSON)
		            .content(mapper.writeValueAsString(todoItems).getBytes(StandardCharsets.UTF_8))
		            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
					andExpect(jsonPath("$.message").value("Todo added successfully"));
		
	}
	
	@Test
	void UpdateTodoTest() throws Exception {
		Long id = 1l;
		mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/todo//updateTodo/{id}", id)
				 .contentType(MediaType.APPLICATION_JSON)
		            .content(mapper.writeValueAsString(todoItems).getBytes(StandardCharsets.UTF_8))
		            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	
	
	@Test
	void removeTodoTest() throws Exception {
		Long id = 1l;
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/todo/removeTodo/{id}", id);
		MvcResult result = mvc.perform(request).andReturn();
		assertNotNull(result.getResponse().getContentAsString());
	}
	


}
