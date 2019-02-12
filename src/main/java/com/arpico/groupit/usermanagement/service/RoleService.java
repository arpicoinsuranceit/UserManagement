package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;

public interface RoleService {

	public String save(RoleDto roleDto) throws Exception;

	public List<RoleDto> getAll() throws Exception;

	public List<RoleDto> getAll(String val)  throws Exception;
	
	RoleDto getSelectedRole(String id)throws Exception;
	
	public String edit(RoleDto roleDto) throws Exception;
	
	public List<RoleDto> getUserRoles(String id)throws Exception;
	
	String removerole(UserAssignDto userAssignDto)throws Exception;
	
	String removeroleMenus(RoleDto roleDto)throws Exception;
}
