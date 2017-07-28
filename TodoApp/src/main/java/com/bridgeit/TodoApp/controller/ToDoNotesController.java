package com.bridgeit.TodoApp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.Json.Response;
import com.bridgeit.TodoApp.Json.TodoNotesResponse;
import com.bridgeit.TodoApp.model.ToDoNotes;
import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.ToDoService;

/**
 * @author Miral
 *
 */
@RestController
public class ToDoNotesController {

	@Autowired
	ToDoService doService;

	// ----------------------------------Create--a--New--Notes--------------------------
	/**
	 * Create a new notes
	 * 
	 * @param doNotesModel
	 * @param result
	 * @param pRequest
	 * @param pResponse
	 * @return
	 */
	// ----------------------------------Create--a--New--Notes--------------------------
	@RequestMapping(value = "/createNote", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> createNotes(@RequestBody ToDoNotes doNotesModel, BindingResult result,
			HttpServletRequest pRequest, HttpServletResponse pResponse) {

		/*
		 * if (result.hasErrors()) {
		 * 
		 * ErrorResponse errorResponse = new ErrorResponse();
		 * errorResponse.setStatus(-1);
		 * errorResponse.setMessage("Invalid Credential"); return new
		 * ResponseEntity<Response>(errorResponse, HttpStatus.NOT_ACCEPTABLE); }
		 */
		System.out.println("inside the create Notes");
		try {

			// ------Getting the Session
			HttpSession httpSession = pRequest.getSession();
			UserRegistration user = (UserRegistration) httpSession.getAttribute("user");
			doNotesModel.setUser(user);
			doNotesModel.setDate(new Date());

			// ------setting into the DataBase
			doService.createNotes(doNotesModel);

			// ------Setting Response
			Response response = new Response();
			response.setStatus(1);
			response.setMessage("Successfully added");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

			// ------Setting Response
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Exception Occur");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------------Search--by--Title-------------------------------
	/**
	 * 
	 * This is the code for the search all notes by their title
	 * 
	 * @param title     {@link Map}
	 * @param result    {@link BindingResult}
	 * @param pRequest  {@link HttpServletRequest}  
	 * @param pResponse {@link HttpServletResponse}
	 * @return			{@link ResponseEntity}
	 */
	// ----------------------------------Search--by--Title-------------------------------
	@RequestMapping(value = "/searchByTitle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> searchByTitle(@RequestBody Map<String, String> title, BindingResult result,
			HttpServletRequest pRequest, HttpServletResponse pResponse) {

		// ------Getting Session
		HttpSession httpSession = pRequest.getSession();
		UserRegistration user = (UserRegistration) httpSession.getAttribute("user");

		List<Object> list = new ArrayList<Object>();
		list.add(title.get("title"));
		list.add(user.getId());

		try {
			// ------Searching all the data of login user by his title
			List<ToDoNotes> list2 = doService.searchByTitle(list);

			// ------Setting Response
			TodoNotesResponse response = new TodoNotesResponse();
			response.setStatus(1);
			response.setMessage("Successfully retrived");
			response.setList(list2);
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();

			// ------Setting Response
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Exception Occur");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ----------------------------------Search--by--ID---------------------------------
	/**	
	 * 
	 * This is code for the search all notes information by specific user
	 * 
	 * @param id 		{@link String}
	 * @param pRequest	{@link HttpServletRequest}
	 * @param pResponse	{@link HttpServletResponse}	
	 * @return 			{@link ResponseEntity}
	 */
	// ----------------------------------Search--by--ID---------------------------------
	@RequestMapping(value = "searchById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> searchBYId(@PathVariable("id") String id, HttpServletRequest pRequest,
			HttpServletResponse pResponse) {

		// ------Getting Session
		HttpSession httpSession = pRequest.getSession();
		UserRegistration user = (UserRegistration) httpSession.getAttribute("user");

		try {

			// ------Searching all the data of login user by his id
			List<ToDoNotes> list2 = doService.searchById(user.getId());

			// ------Setting Response
			TodoNotesResponse response = new TodoNotesResponse();
			response.setStatus(1);
			response.setMessage("Successfully retrived");
			response.setList(list2);
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();

			// ------Setting Response
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Exception Occur");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	// ----------------------------------Update--Notes--------------------------------------
	/**
	 * This is code for the update the todoNotes
	 * 
	 * @param doNotes	{@link ToDoNotes}	
	 * @param pRequest	{@link HttpServletRequest}
	 * @param pResponse	{@link HttpServletResponse}
	 * @return			{@link ResponseEntity}
	 */
	// ----------------------------------Update--Notes--------------------------------------
	@RequestMapping(value = "/updateNotes/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateNote(@PathVariable("id") int id,@RequestBody ToDoNotes doNotes, HttpServletRequest pRequest,
			HttpServletResponse pResponse) {
		
		doNotes.setId(id);
		System.out.println(doNotes);
		// ------Getting Session
		HttpSession httpSession = pRequest.getSession();
		UserRegistration user = (UserRegistration) httpSession.getAttribute("user");

		//doNotes.setId(user.getId());
		doNotes.setUser(user);
		System.out.println("inside the update Notes"+doNotes);
		try {
			ToDoNotes doNotes2 = doService.updateNote(doNotes);

			// ------Setting Response
			TodoNotesResponse response = new TodoNotesResponse();
			response.setStatus(1);
			response.setMessage("Successfully update");
			response.setTodoNotes(doNotes2);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			// ------Setting Response
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Exception Occur");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	// ----------------------------------delete--Notes--------------------------------------
	/**
	 * 
	 * Delete Notes		
	 * 
	 * @param doNotes	{@link ToDoNotes}
	 * @param pRequest	{@link HttpServletRequest}
	 * @param pResponse	{@link HttpServletResponse}
	 * @return			{@link ResponseEntity}
	 */
	// ----------------------------------delete--Notes--------------------------------------
	@RequestMapping(value = "deleteNotes/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteNotes(@PathVariable("id") int id, HttpServletRequest pRequest,
			HttpServletResponse pResponse) {

		// ------Getting Session
		/*HttpSession httpSession = pRequest.getSession();
		UserRegistration user = (UserRegistration) httpSession.getAttribute("user");*/
		System.out.println("inside the delete id is "+id);
		ToDoNotes doNotes = new ToDoNotes();
		doNotes.setId(id);
		
		try {
			ToDoNotes doNotes2 =  doService.deleteNote(doNotes);

			// ------Setting Response
			TodoNotesResponse response = new TodoNotesResponse();
			response.setStatus(1);
			response.setMessage("Successfully delete");
			response.setTodoNotes(doNotes2);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			// ------Setting Response
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Exception Occur");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
