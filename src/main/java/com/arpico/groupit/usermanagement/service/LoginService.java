package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface LoginService {

	public LoginResponseDto isUser(String userName, String password, String subSbu) throws Exception;

	public LoginResponseDto changePassword(String userName, String password, String newPassword,
			String confirmNewPassword) throws Exception;

	public List<SysUserModel> getAll() throws Exception;
	
	;
}
