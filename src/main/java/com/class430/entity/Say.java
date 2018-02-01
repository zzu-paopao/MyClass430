package com.class430.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="say")
public class Say {

	@Id
	@GeneratedValue
	private Integer id;
	private String content;
	private Date time;
	
	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="uid")
//	private User user;
	public synchronized Integer getId() {
		return id;
	}
	public synchronized void setId(Integer id) {
		this.id = id;
	}
	public synchronized String getContent() {
		return content;
	}
	public synchronized void setContent(String content) {
		this.content = content;
	}
	public synchronized Date getTime() {
		return time;
	}
	public synchronized void setTime(Date time) {
		this.time = time;
	}
	
//	public synchronized User getUser() {
//		return user;
//	}
//	public synchronized void setUser(User user) {
//		this.user = user;
//	}
	public Say() {
		// TODO Auto-generated constructor stub
	}
}
