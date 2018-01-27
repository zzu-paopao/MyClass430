package com.class430.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.class430.dao.UserRepository;
import com.class430.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/getInformation")
	public String getInformation(HttpSession session,HttpServletRequest request){
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userRepository.getOne(userId);
		if(user!=null){
			request.setAttribute("user", user);
			return "modify";
		}
		return "error";
	}
	
	@RequestMapping("/toModify")
	public String modifyInformation(@RequestParam(name="password",defaultValue="0") String password
			,@RequestParam(name="phone",defaultValue="0")String phone
			,@RequestParam(name="name",defaultValue="泡泡")String name
			,HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userRepository.getOne(userId);
		if(user!=null){
			user.setName(name);
			user.setPassword(password);
			user.setPhone(phone);
			return "redirect:/toHome";
		}
		
		return "error";
		
	}
	
	@RequestMapping("/getAll")
	public String all(HttpServletRequest request){
		List<User> alluser = userRepository.findAll();
		request.setAttribute("all", alluser);
		return "all";
	}
}
