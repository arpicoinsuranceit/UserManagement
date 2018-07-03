package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.model.SysUser;

public interface LoginService {

	public LoginResponseDto isUser(String userName, String password) throws Exception;

	public LoginResponseDto changePassword(String userName, String password, String newPassword,
			String confirmNewPassword) throws Exception;

	public List<SysUser> getAll() throws Exception;
	
	;
}
