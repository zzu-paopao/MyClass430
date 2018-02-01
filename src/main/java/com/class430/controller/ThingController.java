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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.class430.dao.SayRepository;
import com.class430.dao.ThingRepository;
import com.class430.entity.Say;
import com.class430.entity.Thing;

@Controller
public class ThingController {

	@Autowired
	ThingRepository thingRepository;
	
	@Autowired
	SayRepository sayRepository;
	
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request,@RequestParam(name="page",defaultValue="0")Integer page){
		
		Page<Thing> things = thingRepository.findAll(new PageRequest(page, 9));
		
		List<Say> says = sayRepository.findAll(new Sort(Direction.DESC, "time"));
		if(says.size()>8){
			says.subList(0, 8);
		}else{
			says.subList(0, says.size());
		}
		
		request.setAttribute("things", things.getContent());
		request.setAttribute("says", says);
		
		return "home";
	}
	
	@RequestMapping("/getOneThing/{thingId}")
	public String getOneThing(@PathVariable Integer thingId,HttpServletRequest request){
		Thing thing = thingRepository.findOneById(thingId);
		request.setAttribute("thing", thing);
		return "thing";
	}
	
	@RequestMapping("/toModifyOneThing/{thingId}")
	public String toModifyOneThing(@PathVariable Integer thingId,HttpServletRequest request){
		Thing thing = thingRepository.findOneById(thingId);;
		request.setAttribute("thing", thing);
		return "modifything";
	}
	
	@RequestMapping(value="/insertThing",method=RequestMethod.POST)
	public String insertThing(@RequestParam(name="title",defaultValue="无标题")String title
			,@RequestParam(name="content",defaultValue="无内容") String content
			,@RequestParam(name="picture",required=false) MultipartFile picture
			,HttpSession session,HttpServletRequest request){
		
		Thing thing = new Thing();
		thing.setTime(new Date());
		thing.setContent(content);
		thing.setTitle(title);
		thing.setUid((Integer)session.getAttribute("userId"));
		
		String filename;
		if(picture!=null){
			try {
				filename=UUID.randomUUID().toString().substring(0,9).toString()
						+picture.getOriginalFilename();
				File file = new File(request.getRealPath("/web/picture/")+filename);
				FileUtils.copyInputStreamToFile(picture.getInputStream()
						, file);
				
				thing.setPicture("/web/picture/"+filename);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			thing.setPicture("/web/picture/timg.jpg");
		}
		thingRepository.save(thing);
		
		return "redirect:/toHome";
	}
	
	@RequestMapping(value="/insertSay",method=RequestMethod.POST)
	public String insertSay(@RequestParam(name="content",defaultValue="无内容") String content
			,HttpSession session,HttpServletRequest request){
		
		Say say = new Say();
		say.setTime(new Date());
		say.setContent(content);
		
		sayRepository.save(say);
		
		return "redirect:/toHome";
	}
	
	
	@RequestMapping("/modifyThing/{thingId}")
	public String modifyThing(@PathVariable(name="thingId")Integer thingId
			,@RequestParam(name="title",defaultValue="无标题")String title
			,@RequestParam(name="content",defaultValue="无内容") String content
			,@RequestParam(name="picture",required=false)MultipartFile picture
			,HttpSession session,HttpServletRequest request){
		
		Thing thing = thingRepository.findOneById(thingId);
		thing.setTime(new Date());
		thing.setContent(content);
		thing.setTitle(title);
		thing.setUid((Integer)session.getAttribute("userId"));
		
		String filename;
		if(picture!=null){
			try {
				filename=UUID.randomUUID().toString().substring(0,9).toString()
						+picture.getOriginalFilename();
				File file = new File(request.getRealPath("/web/picture/")+filename);
				FileUtils.copyInputStreamToFile(picture.getInputStream()
						, file);
				
				thing.setPicture("/web/picture/"+filename);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		thingRepository.saveAndFlush(thing);
		
		return "redirect:/getMyThing";
	}
	
	@RequestMapping("/deleteOneThing/{id}")
	public String deleteMyThing(@PathVariable(name="id")Integer thingId){
		thingRepository.delete(thingId);
		
		return "redirect:/getMyThing";
	}
	
	@RequestMapping("/getMyThing")
	public String getMyThing(HttpSession session,HttpServletRequest request){
		Integer userId = (Integer) session.getAttribute("userId");
		List<Thing> myThings = thingRepository.getByUid(userId);
		request.setAttribute("myThings", myThings);
		
		return "mypublish";
	}
}
