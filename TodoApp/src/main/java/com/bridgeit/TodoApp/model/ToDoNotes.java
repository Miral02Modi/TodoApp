package com.bridgeit.TodoApp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ToDoNotes {
	
	int id;
	String title;
	String description;
	Date date;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	UserRegistration user;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public UserRegistration getUser() {
		return user;
	}
	public void setUser(UserRegistration user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "ToDoNotes [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", user=" + user + "]";
	}
	
	
	
}
