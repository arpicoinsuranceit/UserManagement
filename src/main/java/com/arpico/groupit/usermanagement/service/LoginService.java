package com.arpico.groupit.usermanagement.service;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;

public interface LoginService {

	public LoginResponseDto isUser(String userName, String password) throws Exception;

	public LoginResponseDto changePassword(String userName, String password, String newPassword,
			String confirmNewPassword) throws Exception;;
}
