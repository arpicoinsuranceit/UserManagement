package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.SbuDao;
import com.arpico.groupit.usermanagement.dao.SubSbuDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.SbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private SubSbuDao subSbuDao;
	
	@Autowired
	private SbuDao sbuDao;

	@Override
	public List<MenuDto> getMenu(String type, String id) throws Exception {
		List<MenuDto> dtos = new ArrayList<>();

		List<MenuModel> menuModels = new ArrayList<>();

		switch (type) {
		case "SBU":
			SbuModel model = sbuDao.findOne(id);
			model.getSbuModels().forEach(e -> {
				e.getMenuModels().forEach(menu -> {
					menuModels.add(menu);
				});
			});
			
			break;

		case "SUBSBU":
			SubSbuModel subSbuModel = subSbuDao.findOne(id);
			subSbuModel.getMenuModels().forEach(e -> {
				menuModels.add(e);
			});
			
			break;

		default:
			break;
		}

		menuModels.forEach(e -> {
			dtos.add(getMenuDto(e));
		});
		
		return dtos;

	}

	private MenuDto getMenuDto(MenuModel e) {
		MenuDto dto = new MenuDto();
		
		dto.setMenuName(e.getMenuName());
		dto.setMenuDescription(e.getMenuDescription());
		dto.setMenuId(e.getMenuId());
		dto.setSystem(e.getSubSbuModel().getSubSbuName());
		
		return dto;
	}

}
