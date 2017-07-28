package com.bridgeit.TodoApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="ToDoNotes")
@SuppressWarnings("serial")
public class ToDoNotes implements Serializable {
	
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	private int id;
	private  String title;
	private  String description;
	private  Date date;
	
	@ManyToOne(/*cascade=CascadeType.ALL,*/optional=false)
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
