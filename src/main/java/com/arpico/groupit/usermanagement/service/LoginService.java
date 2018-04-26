package com.arpico.groupit.usermanagement.service;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;

public interface LoginService {

	public LoginResponseDto isUser(String userName, String password) throws Exception;
}
