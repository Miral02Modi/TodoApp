package com.bridgeit.TodoApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Miral Modi
 */
@Entity
@Table(name="Registation")
public class UserRegistration {
	
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	int id;
	String name;
	@Column(unique = true)
	String email;
	String password;
	@Column(unique = true)
	String phone;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserRegistration [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + "]";
	}
}
