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
	public String login(@RequestParam(name="username",defaultValue="无") String username,
						@RequestParam(name="password",defaultValue="无") String password
						,HttpSession session){
		
		User user = userRepository.getTop1ByUsernameAndPassword(username, password);
		
		if(user!=null){
			session.setAttribute("userId", user.getUserId());
			return "redirect:/toHome";
		}
		
		return "error";
	}
	
	@RequestMapping("/out")
	public String out(HttpSession session){
		session.setAttribute("userId", null);
		return "redirect:/toHome";
		
	}
	
	@RequestMapping("/toRegister")
	public String register(@RequestParam(name="username",defaultValue="未填写") String username
			,@RequestParam(name="password",defaultValue="未填写") String password
			,@RequestParam(name="phone",defaultValue="未填写")String phone
			,@RequestParam(name="name",defaultValue="未填写")String name){
		
		if(!username.equals("未填写")){
			User user = userRepository.getTop1ByUsername(username);
			
			if(user==null){
				user = new User();
				user.setUsername(username);
				user.setName(name);
				user.setPassword(password);
				user.setPhone(phone);
				userRepository.saveAndFlush(user);
				return "redirect:/toHome";
			}else{
				return "error";
			}
		}else{
			return "error";
		}
		
		
		
		
	}
}
