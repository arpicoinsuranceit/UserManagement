package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.SbuDto;
import com.arpico.groupit.usermanagement.dto.SysUserDto;
import com.arpico.groupit.usermanagement.service.SysUserService;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin()
@RequestMapping("sysUser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@Value("${server.context-path}")
	private String path;
	
	@Autowired
	ServletContext context;
	
	public List<MenuDto> getMenuByUser(@RequestParam String token){
		try {
			return sysUserService.getAllByUser(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	 @RequestMapping("AllUsers")
	public ModelAndView navAllUsers () throws Exception {
    	context.setAttribute("path", path);
		
    	
    	ModelAndView mav = new ModelAndView("pages/user/AllUsers");
		
		mav.addObject("title", "USER | ALL USERS");
		return mav;
 }
	 
	 
	
	 @RequestMapping("addUser")
		public ModelAndView navAddRole () throws Exception {
	    	context.setAttribute("path", path);
			
	    	
	    	ModelAndView mav = new ModelAndView("pages/user/AddUser");
			
			mav.addObject("title", "USER | ADD USER");
			return mav;
	 }
	 
	 @RequestMapping(value = "savesysuser")
	 @ResponseBody
		public ResponseEntity<Object> saveSysUser (@RequestBody SysUserDto sysUserDto) throws Exception {
	    	System.out.println("Sucsesss");
	    	
		 	sysUserService.saveSysUser(sysUserDto);
			
	    	return new ResponseEntity<>("Work",HttpStatus.OK);
			
	 }
	 
	 @GetMapping("/get_Usercode/{userName}")
	 @ResponseBody
	 public String searchUserCode(@PathVariable String userName) throws Exception {
			System.out.println(userName);
	    	return sysUserService.searchUserCode(userName); 
		}
	  
}
