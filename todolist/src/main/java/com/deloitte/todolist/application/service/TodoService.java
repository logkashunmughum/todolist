package com.deloitte.todolist.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	List<HashMap<String,String>> loginCredentials = new ArrayList<>();
	HashMap<String,String> user = new HashMap<>();
	
	public List<HashMap<String,String>> addUserLogin() {
		user.put("Lokesh", "12345");
		user.put("test","pwd123");
		loginCredentials.add(user);
		return loginCredentials;
	}
	
	public Boolean checkUserLogin(String userName, String password){
		addUserLogin();
		for(HashMap<String, String> cred : loginCredentials) {
		if(cred.containsKey(userName) && cred.containsValue(password)) {
			return true;
		}}
		return false;
		
	}
}
