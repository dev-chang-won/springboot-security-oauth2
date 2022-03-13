package com.ch1.springbootsecurityoauth2.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )  //DB의 방식을 지원
	private int user_seq;
	
	private String username;
	private String password;
	private String email;
	
	private String role;
	private String provider;
	private String providerId;
	
	private Timestamp regDttm;
	private Timestamp lastDttm;
	
	///////////////////////////////
	
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Timestamp getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(Timestamp regDttm) {
		this.regDttm = regDttm;
	}
	public Timestamp getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(Timestamp lastDttm) {
		this.lastDttm = lastDttm;
	}

}