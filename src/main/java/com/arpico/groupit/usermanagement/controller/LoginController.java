package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.model.SysUser;
import com.arpico.groupit.usermanagement.service.LoginService;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseDto login(@RequestParam("userName") String userName,
			@RequestParam("password") String password) throws Exception {
		
		return loginService.isUser(userName, password);
		//return null;
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public LoginResponseDto changePassword(@RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword) throws Exception {
		
		return loginService.changePassword(userName, password, newPassword, confirmNewPassword);
		//return null;
	}
	
	@RequestMapping(value = "/testAll", method = RequestMethod.GET)
	public List<SysUser> getAll() throws Exception {
		
		return loginService.getAll();
		//return null;
	}

	
}
