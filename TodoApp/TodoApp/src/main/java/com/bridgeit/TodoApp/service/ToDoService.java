package com.bridgeit.TodoApp.service;

import java.util.List;

import com.bridgeit.TodoApp.model.Collabrator;
import com.bridgeit.TodoApp.model.ToDoNotes;

/**
 * @author Miral
 *
 */
public interface ToDoService {
	
	public int createNotes(ToDoNotes doNotesModel) throws Exception;
	
	public List<ToDoNotes> searchByTitle(List<Object> list) throws Exception;
	
	public List<ToDoNotes> searchById(int id) throws Exception;
	
	public ToDoNotes updateNote(ToDoNotes doNotes) throws Exception;
	
	public ToDoNotes deleteNote(ToDoNotes doNotes) throws Exception;
	
	public ToDoNotes archivedNotes(ToDoNotes doNotes) throws Exception;
	
	public int getOwnerId(String email) throws Exception; 
	
	public void createCollbrator(Collabrator collabrator) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List getSharedNotes(int sharedId) throws Exception;
	
}
