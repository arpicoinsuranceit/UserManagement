package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.MenuDto;

public interface SysUserService {
	 List<MenuDto> getAllByUser(String referance) throws Exception;
}
