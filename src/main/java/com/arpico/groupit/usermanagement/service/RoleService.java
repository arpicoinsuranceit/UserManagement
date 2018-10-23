package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.RoleDto;

public interface RoleService {

	public String save(RoleDto roleDto) throws Exception;

	public List<RoleDto> getAll() throws Exception;

	public List<RoleDto> getAll(String val)  throws Exception;
}
