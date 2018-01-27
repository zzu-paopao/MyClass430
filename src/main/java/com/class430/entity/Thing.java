package com.class430.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="thing")
public class Thing {

	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String content;
	private Date time;
	private String picture;
	
	@Column(insertable=false,updatable=false)
	private Integer uid;
	
	@ManyToOne
	@JoinColumn(name="uid")
	private User user;
	public synchronized Integer getId() {
		return id;
	}
	public synchronized void setId(Integer id) {
		this.id = id;
	}
	public synchronized String getTitle() {
		return title;
	}
	public synchronized void setTitle(String title) {
		this.title = title;
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
	public synchronized String getPicture() {
		return picture;
	}
	public synchronized void setPicture(String picture) {
		this.picture = picture;
	}
	public synchronized Integer getUid() {
		return uid;
	}
	public synchronized void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public synchronized User getUser() {
		return user;
	}
	public synchronized void setUser(User user) {
		this.user = user;
	}
	public Thing() {
		// TODO Auto-generated constructor stub
	}
}
