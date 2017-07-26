package com.bridgeit.TodoApp.dao;

import java.util.List;

import com.bridgeit.TodoApp.model.ToDoNotes;

/**
 * @author Miral
 *
 */
public interface ToDoNotesDao {
	
	public ToDoNotes createNotes(ToDoNotes doNotesModel) throws Exception;
	
	public List<ToDoNotes> searchByTitle(List<Object> list) throws Exception;
	
	public List<ToDoNotes> searchById(int id) throws Exception;
	
	public ToDoNotes updateNote(ToDoNotes doNotes) throws Exception;
	
	public ToDoNotes deleteNote(ToDoNotes doNotes) throws Exception;
	
	public ToDoNotes archivedNotes(ToDoNotes doNotes) throws Exception;
}
