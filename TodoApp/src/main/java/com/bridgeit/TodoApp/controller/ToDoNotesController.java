package com.bridgeit.TodoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgeit.TodoApp.service.ToDoService;

public class ToDoNotesController {
	
	@Autowired
	ToDoService doService;
	
	@RequestMapping(value="/createNote",method = RequestMethod.POST)
	public RequestEntity<Void> generateNotes(){
		return null;
	}
	
}
