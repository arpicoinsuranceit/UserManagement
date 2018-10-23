package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.MenuDao;
import com.arpico.groupit.usermanagement.dao.RoleDao;
import com.arpico.groupit.usermanagement.dao.RoleMenuDao;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.RoleMenuModel;
import com.arpico.groupit.usermanagement.model.RoleModel;
import com.arpico.groupit.usermanagement.service.RoleService;
import com.arpico.groupit.usermanagement.util.AppConstant;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;

	@Override
	public String save(RoleDto roleDto) throws Exception {
		RoleModel model = getRoleModel(roleDto);

		List<RoleMenuModel> roleMenuModels = new ArrayList<>();

		roleDto.getMenus().forEach(e -> {
			roleMenuModels.add(getRoleMenuModel(e, model));
		});

		roleDao.save(model);
		roleMenuDao.save(roleMenuModels);

		return "200";
	}

	private RoleMenuModel getRoleMenuModel(String e, RoleModel roleModel) {
		MenuModel menuModel = menuDao.findOne(e);

		RoleMenuModel model = new RoleMenuModel();
		model.setEnabled(AppConstant.ENABLE);
		model.setId(UUID.randomUUID().toString());
		model.setRoleModel(roleModel);
		model.setMenuModel(menuModel);

		return model;
	}

	private RoleModel getRoleModel(RoleDto roleDto) {
		RoleModel model = new RoleModel();
		model.setCreateDate(new Date());
		model.setDescription(roleDto.getDescription());
		model.setEnable(AppConstant.ENABLE);
		model.setId(UUID.randomUUID().toString());
		model.setName(roleDto.getName());
		return model;
	}

	@Override
	public List<RoleDto> getAll() throws Exception {
		List<RoleModel> roleModels = (List<RoleModel>) roleDao.findAll();

		List<RoleDto> dtos = new ArrayList<>();
		roleModels.forEach(e -> {
			dtos.add(getRoleDto(e));
		});

		return dtos;

	}

	private RoleDto getRoleDto(RoleModel e) {
		RoleDto dto = new RoleDto();
		dto.setDescription(e.getDescription());
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setSbu("450");
		return dto;
	}

	@Override
	public List<RoleDto> getAll(String val) throws Exception {
		List<RoleModel> roleModels = null;
		if(val.equals("No_Val")) {
			roleModels = (List<RoleModel>) roleDao.findAll();
		}else {
			roleModels = (List<RoleModel>) roleDao.findAllByNameContaining(val);
		}
		
		 

		List<RoleDto> dtos = new ArrayList<>();
		roleModels.forEach(e -> {
			dtos.add(getRoleDto(e));
		});

		return dtos;
	}

}
