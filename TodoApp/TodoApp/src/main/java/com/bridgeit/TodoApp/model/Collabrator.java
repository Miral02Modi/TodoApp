package com.bridgeit.TodoApp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="collabrator")
public class Collabrator implements Serializable {
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	private int id;
	private int noteId;
	private int owner;
	private int sharedWith;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public int getSharedWith() {
		return sharedWith;
	}
	public void setSharedWith(int sharedWith) {
		this.sharedWith = sharedWith;
	}
	
	@Override
	public String toString() {
		return "Collabrator [id=" + id + ", noteId=" + noteId + ", owner=" + owner + ", sharedWith=" + sharedWith + "]";
	}
	
	
}
