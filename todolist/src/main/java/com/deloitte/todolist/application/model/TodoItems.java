package com.deloitte.todolist.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Configuration
public class TodoItems {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String title;
	private boolean isDone;
	private String description;
	
	public TodoItems() {
		
	}

	public TodoItems(Long id, String title, boolean isDone, String description) {
		super();
		this.id = id;
		this.title = title;
		this.isDone = isDone;
		this.description = description;
	}

}
