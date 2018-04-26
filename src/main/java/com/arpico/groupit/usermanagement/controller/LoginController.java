package com.arpico.groupit.usermanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseDto requestMethodName(@RequestParam String userName,
			@RequestParam String password) {
		return null;
	}

	
}
