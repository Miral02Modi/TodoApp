package com.bridgeit.TodoApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="PageScaper")
@SuppressWarnings("serial")
public class PageScraper implements Serializable {
	
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	int id;
	String url;
	String titleUrl;
	String hostName;
	String mainUrl; 
	
	@ManyToOne(/*cascade=CascadeType.ALL,*/optional=false)
	@JoinColumn(name="userId1")
	UserRegistration user;
	@JoinColumn(name="noteId")
	int noteId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getMainUrl() {
		return mainUrl;
	}
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getTitleUrl() {
		return titleUrl;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	
	
	public UserRegistration getUser() {
		return user;
	}
	public void setUser(UserRegistration user) {
		this.user = user;
	}
	
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	@Override
	public String toString() {
		return "PageScraper [id=" + id + ", url=" + url + ", titleUrl=" + titleUrl + ", hostName=" + hostName
				+ ", mainUrl=" + mainUrl + ", user=" + user + ", noteId=" + noteId + "]";
	}
	
	
}
