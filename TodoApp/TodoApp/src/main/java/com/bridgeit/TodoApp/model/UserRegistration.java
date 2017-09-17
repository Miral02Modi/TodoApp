package com.bridgeit.TodoApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Miral Modi
 */
@Entity
@Table(name="Registation")
@SuppressWarnings("serial")
public class UserRegistration implements Serializable {
	
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(unique = true)
	private String phone;
	private String varifyUser;
	@Lob
	@Column(name="profilleImage", columnDefinition="LONGBLOB")
	private String profilleImage;
	
	
	public String getProfilleImage() {
		return profilleImage;
	}
	public void setProfilleImage(String profilleImage) {
		this.profilleImage = profilleImage;
	}
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
	
	
	
	public String getVarifyUser() {
		return varifyUser;
	}
	public void setVarifyUser(String varifyUser) {
		this.varifyUser = varifyUser;
	}
	
	@Override
	public String toString() {
		return "UserRegistration [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", varifyUser=" + varifyUser + "]";
	}
	
}
