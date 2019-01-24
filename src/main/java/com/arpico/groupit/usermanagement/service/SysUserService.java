package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.SysUserDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;

public interface SysUserService {
	
	 List<MenuDto> getAllByUser(String referance) throws Exception;

	List<UserTokenDto> getAll(String val) throws Exception;

	String assignUser(UserAssignDto userAssignDto) throws Exception;
	
	RoleDto updateUserRole(String roleId)throws Exception;
	
	String saveSysUser(SysUserDto sysUserDto)throws Exception;

	String searchUserCode(String userName)throws Exception;

}
