package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.service.SysUserService;

@RestController
@CrossOrigin(origins = "*")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	public List<MenuDto> getMenuByUser(@RequestParam String token){
		try {
			return sysUserService.getAllByUser(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
