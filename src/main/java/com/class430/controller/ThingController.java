package com.class430.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.class430.dao.ThingRepository;
import com.class430.entity.Thing;

@Controller
public class ThingController {

	@Autowired
	ThingRepository thingRepository;
	
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request){
		
		Page<Thing> things = thingRepository.findAll(new PageRequest(0, 9));
		request.setAttribute("things", things);
		
		return "home";
	}
	
	@RequestMapping("/getOneThing")
	public String getOneThing(@RequestParam(name="thingId")Integer thingId,HttpServletRequest request){
		Thing thing = thingRepository.findOne(thingId);;
		request.setAttribute("thing", thing);
		return "thing";
	}
	
	@RequestMapping("/insertThing")
	public String insertThing(@RequestParam(name="title",defaultValue="无标题")String title
			,@RequestParam(name="content",defaultValue="无内容") String content
			,@RequestParam(name="picture")MultipartFile picture
			,HttpSession session,HttpServletRequest request){
		
		Thing thing = new Thing();
		thing.setTime(new Date());
		thing.setContent(content);
		thing.setTitle(title);
		thing.setUid((Integer)session.getAttribute("userId"));
		
		if(picture!=null){
			try {
				File file = new File(request.getRealPath("/static/picture/")+UUID.randomUUID().toString().substring(0,9).toString()
						+"."+picture.getOriginalFilename().split(".")[1]);
				FileUtils.copyInputStreamToFile(picture.getInputStream()
						, file);
				
				thing.setPicture(file.getAbsolutePath());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		thingRepository.save(thing);
		
		return "redirect:/getMyThing";
	}
	
	@RequestMapping("/modifyThing")
	public String modifyThing(@RequestParam(name="thingId")Integer thingId
			,@RequestParam(name="title",defaultValue="无标题")String title
			,@RequestParam(name="content",defaultValue="无内容") String content
			,@RequestParam(name="picture")MultipartFile picture
			,HttpSession session,HttpServletRequest request){
		
		Thing thing = thingRepository.findOne(thingId);
		thing.setTime(new Date());
		thing.setContent(content);
		thing.setTitle(title);
		thing.setUid((Integer)session.getAttribute("userId"));
		
		if(picture!=null){
			try {
				File file = new File(request.getRealPath("/static/picture/")+UUID.randomUUID().toString().substring(0,9).toString()
						+"."+picture.getOriginalFilename().split(".")[1]);
				FileUtils.copyInputStreamToFile(picture.getInputStream()
						, file);
				
				thing.setPicture(file.getAbsolutePath());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		thingRepository.saveAndFlush(thing);
		
		return "redirect:/getMyThing";
	}
	
	@RequestMapping("/deleteMyThing")
	public String deleteMyThing(@RequestParam(name="thingId")Integer thingId){
		thingRepository.delete(thingId);;
		
		return "redirect:/getMyThing";
	}
	
	@RequestMapping("/getMyThing")
	public String getMyThing(HttpSession session,HttpServletRequest request){
		Integer userId = (Integer) session.getAttribute("userId");
		List<Thing> myThings = thingRepository.getByUid(userId);
		request.setAttribute("myThings", myThings);
		
		return "mything";
	}
}
