package com.bridgeit.TodoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.dao.ToDoNotesDao;
import com.bridgeit.TodoApp.model.ToDoNotes;

/**
 * @author Miral
 *
 */
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	ToDoNotesDao todoDao;

	@Override
	@Transactional
	public ToDoNotes createNotes(ToDoNotes doNotesModel) throws Exception {
		return todoDao.createNotes(doNotesModel);
	}

	
	@Override
	public List<ToDoNotes> searchByTitle(List<Object> list) throws Exception {
		return todoDao.searchByTitle(list);
	}


	@Override
	public List<ToDoNotes> searchById(int id) throws Exception {
		return todoDao.searchById(id);
	}


	@Override
	@Transactional
	public ToDoNotes updateNote(ToDoNotes doNotes) throws Exception {
		return todoDao.updateNote(doNotes);
	}


	@Override
	@Transactional
	public ToDoNotes deleteNote(ToDoNotes doNotes) throws Exception {
		return todoDao.deleteNote(doNotes);
	}


	@Override
	public ToDoNotes archivedNotes(ToDoNotes doNotes) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
