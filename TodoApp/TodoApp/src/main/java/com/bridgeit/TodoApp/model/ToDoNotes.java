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
	private  String archive;
	private  String pinned; 
	private  String color;
	private  String isTrash; 
	private  String reminderTime; 
	
	
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
	
	
	
	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}
	
	
	public String getPinned() {
		return pinned;
	}
	public void setPinned(String pinned) {
		this.pinned = pinned;
	}
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	public String getIsTrash() {
		return isTrash;
	}
	public void setIsTrash(String isTrash) {
		this.isTrash = isTrash;
	}
	
	
	
	
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	
	
	@Override
	public String toString() {
		return "ToDoNotes [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", archive=" + archive + ", pinned=" + pinned + ", color=" + color + ", isTrash=" + isTrash
				+ ", reminderTime=" + reminderTime + ", user=" + user + "]";
	}
	
}
