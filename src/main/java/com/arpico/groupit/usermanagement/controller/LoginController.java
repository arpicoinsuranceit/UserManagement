package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.service.LoginService;


@RestController
@CrossOrigin()

public class LoginController {
	
	@Value("${server.context-path}")
	private String path;
    
    @Autowired
	ServletContext context;

	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping("/loginnav")
	public ModelAndView navHome () {
		context.setAttribute("path", path);
		ModelAndView mav = new ModelAndView("core/Login");
		
		mav.addObject("title", "LOGIN | USER");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseDto login(@RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam ("subSbu") String subSbu) throws Exception {
		
		return loginService.isUser(userName, password, subSbu);

	}
	
	@RequestMapping("/log")
	public LoginResponseDto setlogin(@RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam ("subSbu") String subSbu) throws Exception {
		
		return loginService.isUser(userName, password, subSbu);

	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public LoginResponseDto changePassword(@RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword) throws Exception {
		return loginService.changePassword(userName, password, newPassword, confirmNewPassword);
	}
	
	@RequestMapping(value = "/testAll", method = RequestMethod.GET)
	public List<SysUserModel> getAll() throws Exception {
		
		return loginService.getAll();

	}

	
}
