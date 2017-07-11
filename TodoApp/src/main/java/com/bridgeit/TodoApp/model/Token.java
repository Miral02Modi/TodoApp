package com.bridgeit.TodoApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Token")
public class Token implements Serializable {
	
	
	
	@Id
	@GenericGenerator(name="id",strategy="increment")
	@GeneratedValue(generator="id")
	
	int id;
	@Column(name="accessToken")
	private String accessToken;
	@Column(name="refreshToken")
	private String refreshToken;
	@Column(name="userId")
	private int userId;
	@Column(name="createOn")
	private Date createOn;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	
	
	@Override
	public String toString() {
		return "Token [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", userId=" + userId
				+ ", onCreate=" + createOn + "]";
	}
	
}
