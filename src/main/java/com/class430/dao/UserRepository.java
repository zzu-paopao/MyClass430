package com.class430.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.class430.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	public User getTop1ByUsernameAndPassword(String username,String password);

	public User getTop1ByUsername(String username);

	public User getOneByUserId(Integer userId);
}
