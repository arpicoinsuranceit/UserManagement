package com.arpico.groupit.usermanagement.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{

	@Override
	public LoginResponseDto isUser(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
