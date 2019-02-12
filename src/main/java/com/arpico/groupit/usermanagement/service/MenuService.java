package com.arpico.groupit.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.SubMenusDto;

@Service
public interface MenuService {
	
	public List<MenuDto> getMenu(String type, String id) throws Exception;

	public String addMenu(MenuDto menuDto)throws Exception;
	
	public String saveMenu(SubMenusDto menuDto)throws Exception;
	
}
