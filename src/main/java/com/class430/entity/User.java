package com.class430.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private Integer userId;
	private String username;
	private String password;
	private String phone;
	private String name;
	private String address;
	private String work;
	
//	@OneToMany(mappedBy="uid")
//	private List<Thing> things;
	
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public synchronized String getWork() {
		return work;
	}
	public synchronized void setWork(String work) {
		this.work = work;
	}
	public synchronized String getPhone() {
		return phone;
	}
	public synchronized void setPhone(String phone) {
		this.phone = phone;
	}
	public synchronized Integer getUserId() {
		return userId;
	}
	public synchronized void setUserId(Integer userId) {
		this.userId = userId;
	}
	public synchronized String getUsername() {
		return username;
	}
	public synchronized void setUsername(String username) {
		this.username = username;
	}
	public synchronized String getPassword() {
		return password;
	}
	public synchronized void setPassword(String password) {
		this.password = password;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
//	public synchronized List<Thing> getThings() {
//		return things;
//	}
//	public synchronized void setThings(List<Thing> things) {
//		this.things = things;
//	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Integer userId){
		this.userId=userId;
		// TODO Auto-generated constructor stub
	}
}
