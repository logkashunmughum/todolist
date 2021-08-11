package com.deloitte.todolist.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.todolist.application.model.TodoItems;

@Repository
public interface TodoRepository extends JpaRepository<TodoItems, Long>{

	

}
