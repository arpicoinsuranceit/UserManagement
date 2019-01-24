package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.MenuDto;

public interface MenuService {
	
	public List<MenuDto> getMenu(String type, String id) throws Exception;

	
	
}
