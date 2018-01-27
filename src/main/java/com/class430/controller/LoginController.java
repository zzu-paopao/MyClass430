package com.class430.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.class430.dao.UserRepository;
import com.class430.entity.User;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/toLogin")
	public String login(@RequestParam(name="username",defaultValue="nothing") String username,
						@RequestParam(name="password",defaultValue="0") String password
						,HttpSession session){
		
		User user = userRepository.getTop1ByUsernameAndPassword(username, password);
		
		if(user!=null){
			session.setAttribute("userId", user.getUserId());
			return "redirect:/toHome";
		}
		
		return "error";
	}
	
	@RequestMapping("/toRegister")
	public String register(@RequestParam(name="username",defaultValue="nothing") String username
			,@RequestParam(name="password",defaultValue="0") String password
			,@RequestParam(name="phone",defaultValue="0")String phone
			,@RequestParam(name="name",defaultValue="张三")String name){
		
		User user = userRepository.getTop1ByUsername(username);
		
		if(user!=null){
			user.setName(name);
			user.setPassword(password);
			user.setPhone(phone);
			userRepository.saveAndFlush(user);
			return "redirect:/toHome";
		}
		
		return "error";
	}
}
