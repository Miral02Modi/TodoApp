package com.bridgeit.TodoApp.Json;

import java.util.List;

import com.bridgeit.TodoApp.model.ToDoNotes;

public class TodoNotesResponse extends Response {
	
	ToDoNotes todoNotes;
	List<ToDoNotes> list;
	
	
	public ToDoNotes getTodoNotes() {
		return todoNotes;
	}
	public void setTodoNotes(ToDoNotes todoNotes) {
		this.todoNotes = todoNotes;
	}
	
	public List<ToDoNotes> getList() {
		return list;
	}
	public void setList(List<ToDoNotes> list) {
		this.list = list;
	}
	
	
	@Override
	public String toString() {
		return "TodoNotesResponse [todoNotes=" + todoNotes + ", list=" + list + "]";
	}
	
}
