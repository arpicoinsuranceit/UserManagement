package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.MenuDao;
import com.arpico.groupit.usermanagement.dao.SbuDao;
import com.arpico.groupit.usermanagement.dao.SubSbuDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.SubMenusDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.SbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.service.MenuService;
import com.arpico.groupit.usermanagement.util.AppConstant;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private SubSbuDao subSbuDao;
	
	@Autowired
	private SbuDao sbuDao;
	
	@Autowired
	private MenuDao menuDao;

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

	@Override
	public String addMenu(MenuDto menuDto) throws Exception {
		
		SubSbuModel subSbuModel=subSbuDao.findOneBysubSbuName(menuDto.getSystem());
		
		MenuModel menuModel=new MenuModel();
		menuModel.setMenuId(UUID.randomUUID().toString());
		menuModel.setMenuName(menuDto.getMenuName());
		menuModel.setMenuDescription(menuDto.getMenuDescription());
		menuModel.setParent(menuDto.getParent());
		menuModel.setHref(menuDto.getHref());
		menuModel.setIcon(menuDto.getIcon());
		menuModel.setIsEnabled(AppConstant.ENABLE);
		menuModel.setLevel(menuDto.getLevel());
		menuModel.setCreatedTime(new Date());
		menuModel.setSubSbuModel(subSbuModel);
		
		menuDao.save(menuModel);
		
		return "work";
	}

	@Override
	public String saveMenu(SubMenusDto menuDto) throws Exception {
		
		List<MenuModel> menu=new ArrayList<MenuModel>();
		String id=UUID.randomUUID().toString();
		SubSbuModel subSbuModel=subSbuDao.findOneBysubSbuName(menuDto.getSystem());
		
		MenuModel menuModel=new MenuModel();
		menuModel.setMenuId(id);
		menuModel.setMenuName(menuDto.getMenuName());
		menuModel.setMenuDescription(menuDto.getMenuDescription());
		menuModel.setParent("0");
		menuModel.setHref(menuDto.getHref());
		menuModel.setIcon(menuDto.getIcon());
		menuModel.setIsEnabled(AppConstant.ENABLE);
		menuModel.setLevel(1);
		menuModel.setCreatedTime(new Date());
		menuModel.setSubSbuModel(subSbuModel);
		
		
		
		
		menuDto.getMenu().forEach(e->{
			System.out.println(e.getMenuDescription());
			MenuModel subModel=new MenuModel();
			subModel.setMenuId(UUID.randomUUID().toString());
			subModel.setMenuName(e.getMenuName());
			subModel.setMenuDescription(e.getMenuDescription());
			subModel.setParent(id);
			subModel.setHref(menuDto.getHref());
			subModel.setIcon(menuDto.getIcon());
			subModel.setIsEnabled(AppConstant.ENABLE);
			subModel.setLevel(2);
			subModel.setCreatedTime(new Date());
			subModel.setSubSbuModel(subSbuModel);
			menu.add(subModel);
		});
		
		menu.add(menuModel);
		menuDao.save(menu);
		
		return "Work";
	}

}
